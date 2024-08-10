package ru.wakeupneo.recruiting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.dto.UserDto;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final TemplateEngine templateEngine;

    public void sendMail(UserDto userDto, MeetingDto meetingDto) {

    }

    private String prepareMessage(UserDto userDto, MeetingDto meetingDto) {
        var context = new Context();
        context.setVariable("name", userDto.getName());
        context.setVariable("surname", userDto.getSurname());
        context.setVariable("direction", meetingDto.getDirectionDto().getName());
        context.setVariable("date", meetingDto.getStartTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        context.setVariable("time", meetingDto.getStartTime().format(DateTimeFormatter.ofPattern("hh-mm")));
        return templateEngine.process("mail_template", context);
    }
}
