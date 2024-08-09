package ru.wakeupneo.recruiting.model;

import ru.wakeupneo.recruiting.model.enums.UserCategory;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String timeZone;
    private List<TimeSlot> freeTimeSlotList;
    @Enumerated(EnumType.STRING)
    private UserCategory category;
    private List<Direction> directionList;
}
