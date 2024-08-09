package ru.wakeupneo.recruiting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.wakeupneo.recruiting.model.enums.MeetingStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meeting")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_meeting")
    private Long id;
    @Column(name = "meeting_name")
    private String name;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MeetingStatus meetingStatus;
    @Column(name = "start_time_date")
    private LocalDateTime startDateTime;
    @Column(name = "duration_min")
    private int durationMin;
    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;
    @Column(name = "ref_files")
    private String ref;
}
