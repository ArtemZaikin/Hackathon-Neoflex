package ru.wakeupneo.recruiting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.wakeupneo.recruiting.model.User;
import ru.wakeupneo.recruiting.service.UserService;
import ru.wakeupneo.recruiting.util.exception.UserNotFoundException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/users", produces = {"application/json"})
@RequiredArgsConstructor
public class UserConstroller {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getUser() {
        try {
            List<User> allUsers = userService.getAllUsers();
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        } catch (UserNotFoundException e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
