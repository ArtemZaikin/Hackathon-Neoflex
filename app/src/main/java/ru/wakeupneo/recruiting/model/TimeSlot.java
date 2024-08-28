package ru.wakeupneo.recruiting.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
public class TimeSlot {
    private LocalDateTime startDateTime;
    private int durationMin;

    public TimeSlot (LocalDateTime startDateTime, LocalDateTime endDateTime){
        this.startDateTime = startDateTime;
        this.durationMin = (int) Duration.between(startDateTime, endDateTime).toMinutes();
    }

    public LocalDateTime getEndDateTime(){
        return startDateTime.plusMinutes(durationMin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return durationMin == timeSlot.durationMin && Objects.equals(startDateTime, timeSlot.startDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, durationMin);
    }
}
