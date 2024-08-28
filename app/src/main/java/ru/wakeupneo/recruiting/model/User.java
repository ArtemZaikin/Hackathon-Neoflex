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

    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;

    @ManyToMany(mappedBy = "participants")
    List<Meeting> meetings;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", timezone='").append(timezone).append('\'');
        sb.append(", category=").append(category);
        sb.append(", direction=").append(direction);
        sb.append('}');
        return sb.toString();
    }
}
