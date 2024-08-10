package ru.wakeupneo.recruiting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.wakeupneo.recruiting.model.TimeSlot;
import ru.wakeupneo.recruiting.model.User;
import ru.wakeupneo.recruiting.model.UserFreeTime;
import ru.wakeupneo.recruiting.repository.UserRepository;
import ru.wakeupneo.recruiting.util.exception.UserNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("user с ID:%s не найден", id)));
    }

    @Override
    public List<User> getAvailableUsersOnTimeSlot(TimeSlot timeSlot) {
        //Todo сделать поиск доступных пользователей по свободному времени
        return List.of();
    }

    @Override
    public List<UserFreeTime> getUserFreeTime(long id) {
        return getUserById(id).getFreeTimeSlotList();
    }

}
