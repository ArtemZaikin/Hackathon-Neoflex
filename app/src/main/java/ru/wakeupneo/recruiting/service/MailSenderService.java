package ru.wakeupneo.recruiting.service;

import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.dto.UserDto;

public interface MailSenderService {
    void sendMail(UserDto userDto, MeetingDto meetingDto);
}
