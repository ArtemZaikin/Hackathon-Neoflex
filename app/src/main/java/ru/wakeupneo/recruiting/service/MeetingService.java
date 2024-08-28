package ru.wakeupneo.recruiting.service;

import ru.wakeupneo.recruiting.dto.MeetingDto;

import java.util.List;


public interface MeetingService {

    MeetingDto getMeeting(Long meetingId);

    void createMeeting(MeetingDto meetingDto);

    void updateMeeting(MeetingDto meetingDto, Long meetingId);

    void deleteMeeting(Long meetingId);

    List<MeetingDto> getAllMeetings();

    void updateInvitationStatus(Long meetingId, Long memberId, boolean agreement);
}
