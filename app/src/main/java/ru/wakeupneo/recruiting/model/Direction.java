package ru.wakeupneo.recruiting.model;

import javax.persistence.*;
import java.util.List;

//@Entity
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "directions")
    private List<User> users;
}
