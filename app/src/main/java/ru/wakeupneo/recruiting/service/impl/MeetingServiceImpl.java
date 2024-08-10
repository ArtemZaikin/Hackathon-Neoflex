package ru.wakeupneo.recruiting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.dto.UserDto;
import ru.wakeupneo.recruiting.mapper.MeetingMapper;
import ru.wakeupneo.recruiting.model.Meeting;
import ru.wakeupneo.recruiting.model.enums.InvitationStatus;
import ru.wakeupneo.recruiting.model.enums.MeetingStatus;
import ru.wakeupneo.recruiting.model.enums.UserCategory;
import ru.wakeupneo.recruiting.repository.MeetingRepository;
import ru.wakeupneo.recruiting.service.MeetingService;
import ru.wakeupneo.recruiting.util.exception.MeetingNotCompleteException;
import ru.wakeupneo.recruiting.util.exception.MeetingNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MeetingMapper meetingMapper;
    private final MeetingRepository meetingRepository;
    private final MemberMeetingServiceImpl memberMeetingService;
    private final MailSenderServiceImpl mailSenderService;

    @Override
    public MeetingDto getMeeting(Long meetingId) {
        var meetingOptional = meetingRepository.findById(meetingId);
        if (meetingOptional.isEmpty()) {
            throw new MeetingNotFoundException(String.format("Встреча с ID:%s не найдена", meetingId));
        }
        return meetingMapper.toMeetingDto(meetingOptional.get());
    }

    @Override
    public void createMeeting(MeetingDto meetingDto) {
        if (!checkParticipants(meetingDto)) {
            throw new MeetingNotCompleteException("Встреча должна содежать все категории пользователей");
        }
        meetingDto.setMeetingStatus(MeetingStatus.APPROVAL);
        var meeting = meetingMapper.toMeeting(meetingDto);
        meetingRepository.save(meetingMapper.toMeeting(meetingDto));
        for (UserDto participant : meetingDto.getParticipants()) {
            //todo занять временной слот у юзера
            var memberMeeting = memberMeetingService.findByUserIdAndMeetingId(participant.getId(), meetingDto.getId());
            memberMeetingService.updateStatus(InvitationStatus.WAITING, memberMeeting);
            mailSenderService.sendMail(participant, meetingDto);
        }
    }

    @Override
    public void updateMeeting(MeetingDto meetingDto, Long meetingId) {
        if (checkParticipants(meetingDto)) {
            var oldMeeting = getMeeting(meetingId);
            if (!oldMeeting.getStartTime().equals(meetingDto.getStartTime())) {
                //todo сделать рассылку об изменении времени встречи
            }
            var meeting = meetingMapper.toMeeting(meetingDto);
            meetingRepository.save(meeting);
            for (UserDto participant : meetingDto.getParticipants()) {
                var memberMeeting = memberMeetingService.findByUserIdAndMeetingId(participant.getId(), meetingDto.getId());
                if (memberMeeting.getInvitationStatus() != null) {
                    //todo занять временной слот у юзера
                    memberMeetingService.updateStatus(InvitationStatus.WAITING, memberMeeting);
                    mailSenderService.sendMail(participant, meetingDto);
                }
            }
        } else {
            cancelMeeting(meetingId);
        }
    }

    @Override
    public void deleteMeeting(Long meetingId) {
        var meeting = getMeeting(meetingId);
        for (UserDto participant : meeting.getParticipants()) {
            //todo освободить временные слоты
            //todo рассылка об отмене встречи
        }
        meetingRepository.deleteById(meetingId);
    }

    @Override
    public List<MeetingDto> getAllMeetings() {
        List<Meeting> meetings = new ArrayList<>();
        meetingRepository.findAll().forEach(meetings::add);
        return meetings.stream().map(meetingMapper::toMeetingDto).toList();
    }

    @Override
    public void updateInvitationStatus(Long meetingId, Long memberId, boolean agreement) {
        if (agreement) {
            memberMeetingService.updateStatus(InvitationStatus.CONFIRMATION,
                    memberMeetingService.findByUserIdAndMeetingId(memberId, meetingId));
            var meeting = getMeeting(meetingId);
            int count = 0;
            for (UserDto participant : meeting.getParticipants()) {
                if (memberMeetingService.findByUserIdAndMeetingId(participant.getId(), meetingId).getInvitationStatus() == InvitationStatus.CONFIRMATION) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == meeting.getParticipants().size()) {
                meeting.setMeetingStatus(MeetingStatus.PLANNED);
            }
        } else {
            memberMeetingService.updateStatus(InvitationStatus.REFUSING,
                    memberMeetingService.findByUserIdAndMeetingId(memberId, meetingId));
            cancelMeeting(meetingId);
        }
    }

    private void cancelMeeting(Long meetingId) {
        var meeting = getMeeting(meetingId);
        meeting.setMeetingStatus(MeetingStatus.CANCELED);
        for (UserDto participant : meeting.getParticipants()) {
            //todo освободить временные слоты
            //todo разослать письма об отмене встречи
        }
    }

    private boolean checkParticipants(MeetingDto meetingDto) {
        var categories = new HashSet<UserCategory>();
        for (UserDto participant : meetingDto.getParticipants()) {
            categories.add(participant.getCategory());
        }
        return categories.size() == 3;
    }
}
