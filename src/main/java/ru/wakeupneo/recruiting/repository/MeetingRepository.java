package ru.wakeupneo.recruiting.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.wakeupneo.recruiting.model.Meeting;

public interface MeetingRepository extends PagingAndSortingRepository<Meeting, Long> {
}
