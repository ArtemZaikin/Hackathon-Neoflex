package ru.wakeupneo.recruiting.repository;

import org.springframework.data.repository.CrudRepository;
import ru.wakeupneo.recruiting.model.Meeting;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {
}
