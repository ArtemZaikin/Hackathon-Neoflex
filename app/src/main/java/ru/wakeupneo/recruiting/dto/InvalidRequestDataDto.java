package ru.wakeupneo.recruiting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidRequestDataDto {

    @Schema(example = "Встреча не найдена")
    private String info;
}