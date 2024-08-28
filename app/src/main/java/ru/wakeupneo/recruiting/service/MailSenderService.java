package ru.wakeupneo.recruiting.service;

import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.dto.UserDto;

public interface MailSenderService {

    void sendInvitationMail(UserDto userDto, MeetingDto meetingDto);

    void sendChangeMeetingMail(UserDto userDto, MeetingDto meetingDto);

    void senCancelMeetingMail(UserDto userDto, MeetingDto meetingDto);

}
