package ru.wakeupneo.recruiting.repository;

import org.springframework.data.repository.CrudRepository;
import ru.wakeupneo.recruiting.model.MemberMeeting;

import java.util.Optional;

public interface MemberMeetingRepository extends CrudRepository<MemberMeeting, Long> {

    Optional<MemberMeeting> findByUserIdAndMeetingId(Long userId, Long projectId);
}
