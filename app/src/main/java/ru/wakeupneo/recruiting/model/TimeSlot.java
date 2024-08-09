package ru.wakeupneo.recruiting.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeSlot {
    private LocalDateTime startDateTime;
    private int durationMin;
}
