package ru.wakeupneo.recruiting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.wakeupneo.recruiting.model.User;
import ru.wakeupneo.recruiting.model.UserFreeTime;
import ru.wakeupneo.recruiting.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/users", produces = {"application/json"})
@RequiredArgsConstructor
public class UserConstroller {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getUser() {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{user_id}/free-time")
    public ResponseEntity<List<UserFreeTime>> getUserFreeTime(@PathVariable Long user_id) {
        return new ResponseEntity<>(userService.getUserFreeTime(user_id), HttpStatus.OK);
    }
}
