package ru.wakeupneo.recruiting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.wakeupneo.recruiting.dto.UserDto;
import ru.wakeupneo.recruiting.model.User;
import ru.wakeupneo.recruiting.model.UserFreeTime;
import ru.wakeupneo.recruiting.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/users", produces = {"application/json"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //TODO возвращать DTO
    @GetMapping()
    public ResponseEntity<List<User>> getUser() {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{user_id}/free-time")
    public ResponseEntity<List<UserFreeTime>> getUserFreeTime(@PathVariable Long user_id) {
        return new ResponseEntity<>(userService.getUserFreeTime(user_id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.setTimezoneUser(userDto), HttpStatus.OK);
    }
}
