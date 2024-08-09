package ru.wakeupneo.recruiting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
