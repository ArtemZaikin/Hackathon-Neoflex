package ru.wakeupneo.recruiting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.wakeupneo.recruiting.dto.InvalidRequestDataDto;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.service.MeetingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meetings")
@Tag(name = "Meeting controller")
public class MeetingController {

    private final MeetingService meetingService;

    @Operation(summary = "Получение встречи по Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Встреча получена"),
            @ApiResponse(responseCode = "400", description = "Ошибка получения встречи", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InvalidRequestDataDto.class)) })
    })
    @GetMapping("/{meeting_id}")
    public ResponseEntity<MeetingDto> getMeeting(@PathVariable Long meeting_id) {
        return ResponseEntity.ok(meetingService.getMeeting(meeting_id));
    }

    @Operation(summary = "Создание встречи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Встреча успешно создана"),
            @ApiResponse(responseCode = "400", description = "Ошибка создания встречи", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InvalidRequestDataDto.class)) })
    })
    @PostMapping("/")
    public void createMeeting(@RequestBody MeetingDto meetingDto) {
        meetingService.createMeeting(meetingDto);
    }

    @Operation(summary = "Обновление встречи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Встреча успешно обновлена"),
            @ApiResponse(responseCode = "400", description = "Ошибка обновления встречи", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InvalidRequestDataDto.class)) })
    })
    @PatchMapping("/{meeting_id}")
    public void updateMeeting(@RequestBody MeetingDto meetingDto, @PathVariable Long meeting_id) {
        meetingService.updateMeeting(meetingDto, meeting_id);
    }

    @Operation(summary = "Удаление встречи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Встреча успешно удалена"),
            @ApiResponse(responseCode = "400", description = "Ошибка удаления встречи", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InvalidRequestDataDto.class)) })
    })
    @DeleteMapping("/{meeting_id}")
    public void deleteMeeting(@PathVariable Long meeting_id) {
        meetingService.deleteMeeting(meeting_id);
    }

    @Operation(summary = "Получение списка всех встреч")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список встреч получен"),
            @ApiResponse(responseCode = "400", description = "Ошибка получения списка встреч", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InvalidRequestDataDto.class)) })
    })
    @GetMapping("/")
    public ResponseEntity<List<MeetingDto>> getAllMeetings() {
        return ResponseEntity.ok(meetingService.getAllMeetings());
    }
}
