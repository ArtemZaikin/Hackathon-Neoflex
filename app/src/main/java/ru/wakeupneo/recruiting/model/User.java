package ru.wakeupneo.recruiting.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import ru.wakeupneo.recruiting.model.enums.UserCategory;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String timezone;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserFreeTime> freeTimeSlotList;

    @Enumerated(EnumType.STRING)
    private UserCategory category;

    @ManyToMany
    @JoinTable(
            name = "members_meetings",
            joinColumns = { @JoinColumn(name = "id_person") },
            inverseJoinColumns = { @JoinColumn(name = "id_meeting") }
    )
    List<Meeting> meetings;

//    @ManyToMany
//    @JoinTable(
//            name = "user_direction",
//            joinColumns = { @JoinColumn(name = "id_user") },
//            inverseJoinColumns = { @JoinColumn(name = "id_direction") }
//    )
//    private List<Direction> directions;
}
