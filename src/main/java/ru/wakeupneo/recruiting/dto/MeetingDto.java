package ru.wakeupneo.recruiting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.wakeupneo.recruiting.model.Direction;
import ru.wakeupneo.recruiting.model.enums.MeetingStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDto {

    String name;
    String description;
    MeetingStatus meetingStatus;
    LocalDateTime startTime;
    int durationMin;
    Direction direction;
    String ref;
}
