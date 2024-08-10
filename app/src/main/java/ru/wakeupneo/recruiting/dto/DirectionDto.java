package ru.wakeupneo.recruiting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectionDto {

    @Schema(example = "103")
    Integer id;

    @Schema(example = "Backend-разработка")
    String name;
}
