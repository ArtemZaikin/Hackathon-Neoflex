package ru.wakeupneo.recruiting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.wakeupneo.recruiting.model.enums.MeetingStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDto {

    Long id;

    @Schema(example = "Собеседование JavaDev")
    String name;

    @Schema(example = "Краткое описание встречи")
    String description;

    @Schema(example = "PLANNED")
    MeetingStatus meetingStatus;

    @Schema(format = "yyyy-mm-dd", example = "2024-01-01")
    LocalDateTime startDateTime;

    @Schema(example = "30")
    int durationMin;

    DirectionDto directionDto;

    @Schema(example = "https://drive.google.com/drive/folders/1TJOd")
    String ref;

    List<UserDto> participants;
}
