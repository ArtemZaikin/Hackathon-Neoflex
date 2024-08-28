package ru.wakeupneo.recruiting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import ru.wakeupneo.recruiting.model.TimeSlot;
import ru.wakeupneo.recruiting.model.enums.UserCategory;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * DTO for {@link ru.wakeupneo.recruiting.model.User}
 */
@Value
public class UserDto {
    @Schema(example = "101")
    Long id;

    @NotEmpty(message = "name not might be empty")
    @Schema(example = "Ivan")
    String name;

    @NotEmpty(message = "surname not might be empty")
    @Schema(example = "Petrov")
    String surname;

    @Email(message = "email should be like login@domain")
    @Schema(example = "ivan@gmail.com")
    String email;

    @Schema(example = "+74959842513")
    String phoneNumber;

    @Schema(example = "Europe/Moscow")
    String timeZone;

    UserCategory category;
    //TODO Заменить TimeSlot на UserFreeTimeDto
    List<List<TimeSlot>> freeTimeList;


}