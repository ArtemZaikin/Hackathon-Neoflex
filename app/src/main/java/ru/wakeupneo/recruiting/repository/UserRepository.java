package ru.wakeupneo.recruiting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.wakeupneo.recruiting.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}