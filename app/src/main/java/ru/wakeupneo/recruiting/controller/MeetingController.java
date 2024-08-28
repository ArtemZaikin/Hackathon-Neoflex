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

    @Operation(summary = "Получение встречи по Id", description = """
            Достает из БД встречу по переданному id.
            В случае успеха в теле ответа передается встреча MeetingDto и код 200,
            в противном случае в теле передается описание ошибки и код 400.
            """)
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

    @Operation(summary = "Создание встречи", description = """
            В теле запроса передается встреча MeetingDto.
            Полученная встреча проверяется на полный состав всех категорий участников и время начала встречи.
            Встреча должна быть запланирована не менее чем за 60 минут до ее начала.
            В случае успешной проверки встрече присваивается статус на утверждении (APPROVAL).
            Встреча сохраняется в БД.
            У всех участников встречи занимаются временные слоты на время, указанное во встрече.
            Всем участникам рассылаются прглашения на встречу, статус приглашения устанавливается
            в ожидание (WAITING).
            В случае успешного создания встречи передается код 200,
            в противном случае в теле ответа передается описание ошибки и код 400.
            """)
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

    @Operation(summary = "Обновление встречи", description = """
            В теле ответа передается обновленная встреча MeetingDto, в переменной пути id встречи.
            Полученная встреча проверяется на полный состав всех категорий участников и время начала встречи.
            Встреча должна быть запланирована не менее чем за 60 минут до ее начала.
            В случае неполного состава участников встреча отменяется.
            Всем участикам рассылаются письма об отмене встречи, их временные слоты освобождаются.
            Встрече присваивается статус отменена (CANCELED).
            В случае успешной проверки на состав участников проверяется зменение даты и времени встречи и наличие новых учатсников встречи.
            При изменения даты или времени всем участникам рассылаются уведомления об изменении даты.
            При зменении состава участников, новым участникам рассылаются приглашения на встречу.
            В случае успешного обновления встречи передается код 200,
            в противном случае в теле ответа передается описание ошибки и код 400.
            """)
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

    @Operation(summary = "Удаление встречи", description = """
            В переменной пути передается id встречи.
            У всех участников встречи освоождаются времпенные слоты.
            встреча удаляется из БД.
            В случае успешного удаления встречи передается код 200,
            в противном случае в теле ответа передается описание ошибки и код 400.
            """)
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

    @Operation(summary = "Получение списка всех встреч", description = """
            Получение списка всех встреч из БД.
            В случае успеха в теле ответа передается список всех встреч List<MeetingDto> и код 200,
            в противном случае в теле передается описание ошибки и код 400.
            """)
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

    @Operation(summary = "Принятие или отказ от приглашения", description = """
            В переменных пути приходят id пользователя и встречи, в параметрах запроса булево значание.
            В случае если в параметре запроса приходит false приглашению присваивается статус отклонено (REFUSING)
            и встреча отменяется. Всем участикам рассылаются письма об отмене встречи, их временные слоты освобождаются.
            Встрече присваивается статус отменена (CANCELED).
            В случае если в параметре запроса приходит true, приглашению присваивается статус подтверждение (CONFIRMATION).
            После этого проверяются статусы приглашений всех участников встречи. Если все участники подтвердили
            свое участие встрече присваивается статус запланирована (PLANNED)
            В случае успешного обновления статуса передается код 200,
            в противном случае в теле ответа передается описание ошибки и код 400.
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Статус приглашения успешно оновлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка обновления статуса", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InvalidRequestDataDto.class)) })
    })
    @PatchMapping("/{meeting_id}/members/{user_id}")
    public void updateInvitationStatus(@PathVariable Long meeting_id,
                                       @PathVariable Long user_id, @RequestParam boolean agreement) {
        meetingService.updateInvitationStatus(meeting_id, user_id, agreement);
    }
}
