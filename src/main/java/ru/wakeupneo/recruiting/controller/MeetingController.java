package ru.wakeupneo.recruiting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.service.MeetingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    @GetMapping("/{meeting_id}")
    public ResponseEntity<MeetingDto> getMeeting(@PathVariable Long meeting_id) {
        return ResponseEntity.ok(meetingService.getMeeting(meeting_id));
    }

    @PostMapping("/")
    public void createMeeting(@RequestBody MeetingDto meetingDto) {
        meetingService.createMeeting(meetingDto);
    }

    @PatchMapping("/{meeting_id}")
    public void updateMeeting(@RequestBody MeetingDto meetingDto, @PathVariable Long meeting_id) {
        meetingService.updateMeeting(meetingDto, meeting_id);
    }

    @DeleteMapping("/{meeting_id}")
    public void deleteMeeting(@PathVariable Long meeting_id) {
        meetingService.deleteMeeting(meeting_id);
    }
}
