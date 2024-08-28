package ru.wakeupneo.recruiting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.wakeupneo.recruiting.model.UserFreeTime;

public interface UserFreeTimeRepository extends JpaRepository<UserFreeTime, Long> {
}