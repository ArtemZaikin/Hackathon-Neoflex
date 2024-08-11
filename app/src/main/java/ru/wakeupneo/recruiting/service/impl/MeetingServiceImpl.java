package ru.wakeupneo.recruiting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.wakeupneo.recruiting.configuration.CommonProps;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.dto.UserDto;
import ru.wakeupneo.recruiting.mapper.MeetingMapper;
import ru.wakeupneo.recruiting.model.Meeting;
import ru.wakeupneo.recruiting.model.enums.InvitationStatus;
import ru.wakeupneo.recruiting.model.enums.MeetingStatus;
import ru.wakeupneo.recruiting.model.enums.UserCategory;
import ru.wakeupneo.recruiting.repository.MeetingRepository;
import ru.wakeupneo.recruiting.service.MeetingService;
import ru.wakeupneo.recruiting.util.exception.MeetingException;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private final CommonProps commonProps;

    @Override
    public MeetingDto getMeeting(Long meetingId) {
        var meetingOptional = meetingRepository.findById(meetingId);
        if (meetingOptional.isEmpty()) {
            throw new MeetingException(String.format("Встреча с ID:%s не найдена", meetingId));
        }
        return meetingMapper.toMeetingDto(meetingOptional.get());
    }

    @Override
    public void createMeeting(MeetingDto meetingDto) {
        if (!checkParticipants(meetingDto)) {
            throw new MeetingException("Встреча должна содежать все категории пользователей");
        }
        if (meetingDto.getStartDateTime().plusMinutes(commonProps.getMinTimeBeforeCreateMeetingMin())
                .isBefore(LocalDateTime.now(ZoneId.of("UTC")))) {
            throw new MeetingException(String.format("Встреча должна быть запланирована не менее чем за %s минут до начала", commonProps.getMinTimeBeforeCreateMeetingMin()));
        }
        meetingDto.setMeetingStatus(MeetingStatus.APPROVAL);
        var meeting = meetingRepository.save(meetingMapper.toMeeting(meetingDto));
        for (UserDto participant : meetingDto.getParticipants()) {
            meetingDto.setId(meeting.getId());
            var memberMeeting = memberMeetingService.findByUserIdAndMeetingId(participant.getId(), meeting.getId());
            memberMeetingService.updateStatus(InvitationStatus.WAITING, memberMeeting);
            mailSenderService.sendInvitationMail(participant, meetingDto);
        }
    }

    @Override
    public void updateMeeting(MeetingDto meetingDto, Long meetingId) {
        if (checkParticipants(meetingDto)) {
            if (meetingDto.getStartDateTime().plusMinutes(commonProps.getMinTimeBeforeCreateMeetingMin())
                    .isBefore(LocalDateTime.now(ZoneId.of("UTC")))) {
                throw new MeetingException(String.format("Встреча должна быть запланирована не менее чем за %s минут до начала", commonProps.getMinTimeBeforeCreateMeetingMin()));
            }
            var oldMeeting = getMeeting(meetingId);
            if (!oldMeeting.getStartDateTime().equals(meetingDto.getStartDateTime())) {
                for (UserDto participant : meetingDto.getParticipants()) {
                    mailSenderService.sendChangeMeetingMail(participant, meetingDto);
                }
            }
            var meeting = meetingRepository.save(meetingMapper.toMeeting(meetingDto));
            for (UserDto participant : meetingDto.getParticipants()) {
                var memberMeeting = memberMeetingService.findByUserIdAndMeetingId(participant.getId(), meeting.getId());
                if (memberMeeting.getInvitationStatus() != null) {
                    memberMeetingService.updateStatus(InvitationStatus.WAITING, memberMeeting);
                    mailSenderService.sendInvitationMail(participant, meetingDto);
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
            mailSenderService.senCancelMeetingMail(participant, meeting);
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
            mailSenderService.senCancelMeetingMail(participant, meeting);
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
