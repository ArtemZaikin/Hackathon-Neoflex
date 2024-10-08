package ru.wakeupneo.recruiting.service;

import ru.wakeupneo.recruiting.dto.UserDto;
import ru.wakeupneo.recruiting.dto.UserFreeTimeDto;
import ru.wakeupneo.recruiting.model.TimeSlot;
import ru.wakeupneo.recruiting.model.User;
import ru.wakeupneo.recruiting.model.UserFreeTime;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    List<User> getAvailableUsersOnTimeSlot(TimeSlot timeSlot);
    List<UserFreeTime> getUserFreeTime(long id);
    User setTimezoneUser(UserDto userDto);
    void saveUserFreeTime(Long userId, UserFreeTimeDto userFreeTimeDto);
    List<UserFreeTime> getUserFreeTimeWithoutMeeting(long id);
}
