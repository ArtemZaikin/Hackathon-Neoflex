package ru.wakeupneo.recruiting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.dto.UserDto;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.wakeupneo.recruiting.service.MailSenderService;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    @Value("")
    private String url;
    private final TemplateEngine templateEngine;

    @Override
    public void sendMail(UserDto userDto, MeetingDto meetingDto) {
        var message = prepareMessage(userDto, meetingDto);
    }

    private String prepareMessage(UserDto userDto, MeetingDto meetingDto) {
        var context = new Context();
        context.setVariable("name", userDto.getName());
        context.setVariable("surname", userDto.getSurname());
        context.setVariable("direction", meetingDto.getDirectionDto().getName());
        context.setVariable("date", meetingDto.getStartTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        context.setVariable("time", meetingDto.getStartTime().format(DateTimeFormatter.ofPattern("hh-mm")));
        context.setVariable("meeting_id", meetingDto.getId());
        context.setVariable("user_id", userDto.getId());
        context.setVariable("url", url);
        return templateEngine.process("mail_template", context);
    }
}
