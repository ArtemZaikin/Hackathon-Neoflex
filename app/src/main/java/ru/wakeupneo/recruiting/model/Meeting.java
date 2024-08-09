package ru.wakeupneo.recruiting.model;

import javax.persistence.*;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_meeting")
    private Long id;
}
