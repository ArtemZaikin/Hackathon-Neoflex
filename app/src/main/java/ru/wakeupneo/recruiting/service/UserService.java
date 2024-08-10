package ru.wakeupneo.recruiting.service;

import ru.wakeupneo.recruiting.model.TimeSlot;
import ru.wakeupneo.recruiting.model.User;
import ru.wakeupneo.recruiting.model.UserFreeTime;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    List<User> getAvailableUsersOnTimeSlot(TimeSlot timeSlot);
    List<UserFreeTime> getUserFreeTime(long id);
}
