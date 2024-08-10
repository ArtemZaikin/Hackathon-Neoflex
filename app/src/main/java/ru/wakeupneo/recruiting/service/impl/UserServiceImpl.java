package ru.wakeupneo.recruiting.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.wakeupneo.recruiting.dto.UserDto;
import ru.wakeupneo.recruiting.model.TimeSlot;
import ru.wakeupneo.recruiting.model.User;
import ru.wakeupneo.recruiting.model.UserFreeTime;
import ru.wakeupneo.recruiting.repository.UserRepository;
import ru.wakeupneo.recruiting.service.UserService;
import ru.wakeupneo.recruiting.util.exception.UserNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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
    @Transactional
    public User setTimezoneUser(UserDto userDto) {
        User curentUser = getUserById(userDto.getId());
        curentUser.setTimezone(userDto.getTimeZone());
        return userRepository.save(curentUser);
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
