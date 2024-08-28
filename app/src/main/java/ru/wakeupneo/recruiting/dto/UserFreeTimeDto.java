package ru.wakeupneo.recruiting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

/**
 * DTO for {@link ru.wakeupneo.recruiting.model.UserFreeTime}
 */
@Value
public class UserFreeTimeDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;
    @Future(message = "Дата должна быть в будущем")
    @Schema(example = "2024-08-13 12:00:00")
    LocalDateTime startDatetime;
    @Positive(message = "Длительность должна быть положительной")
    @Schema(example = "60")
    int durationMin;
}