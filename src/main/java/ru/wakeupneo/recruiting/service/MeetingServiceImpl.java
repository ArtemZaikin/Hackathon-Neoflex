package ru.wakeupneo.recruiting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.mapper.MeetingMapper;
import ru.wakeupneo.recruiting.repository.MeetingRepository;
import ru.wakeupneo.recruiting.util.exception.MeetingNotFoundException;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MeetingMapper meetingMapper;
    private final MeetingRepository meetingRepository;

    @Override
    public MeetingDto getMeeting(long meetingId) {
        var meetingOptional = meetingRepository.findById(meetingId);
        if (meetingOptional.isEmpty()) {
            throw new MeetingNotFoundException();
        }
        return meetingMapper.toMeetingDto(meetingOptional.get());
    }

    @Override
    public void createMeeting(MeetingDto meetingDto) {
        meetingRepository.save(meetingMapper.toMeeting(meetingDto));
    }

    @Override
    public void updateMeeting(MeetingDto meetingDto, Long meetingId) {
        var meeting = meetingMapper.toMeeting(meetingDto);
        meeting.setId(meetingId);
        meetingRepository.save(meeting);
    }

    @Override
    public void deleteMeeting(Long meetingId) {
        meetingRepository.deleteById(meetingId);
    }
}
