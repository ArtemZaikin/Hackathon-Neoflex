package ru.wakeupneo.recruiting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.model.enums.MeetingStatus;
import ru.wakeupneo.recruiting.service.MeetingService;
import ru.wakeupneo.recruiting.service.ScheduledService;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class ScheduledServiceImpl implements ScheduledService {

    private final MeetingService meetingService;

    @Override
    @Scheduled(cron = "0 */15 * * * ?")
    public void checkOverMeetings() {
        var meetings = meetingService.getAllMeetings();
        for (MeetingDto meeting : meetings) {
            if (meeting.getStartTime().isAfter(LocalDateTime.now(ZoneId.of("UTC")))) {
                meeting.setMeetingStatus(MeetingStatus.OVER);
                meetingService.updateMeeting(meeting, meeting.getId());
            }
        }
    }
}
