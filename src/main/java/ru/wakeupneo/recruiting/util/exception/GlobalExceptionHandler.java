package ru.wakeupneo.recruiting.util.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.wakeupneo.recruiting.dto.InvalidRequestDataDto;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<InvalidRequestDataDto> handleMeetingException(MeetingNotFoundException exception) {
        log.warn(exception.getMessage());
        var data = new InvalidRequestDataDto(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<InvalidRequestDataDto> handleUserException(MeetingNotFoundException exception) {
        log.warn(exception.getMessage());
        var data = new InvalidRequestDataDto(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
