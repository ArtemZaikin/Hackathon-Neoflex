package ru.wakeupneo.recruiting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.wakeupneo.recruiting.configuration.CommonProps;
import ru.wakeupneo.recruiting.model.enums.MailSubject;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.dto.UserDto;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.wakeupneo.recruiting.service.MailSenderService;
import ru.wakeupneo.recruiting.util.exception.MailSenderException;

import javax.mail.MessagingException;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    @Value("${spring.mail.username}")
    private String from;
    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;
    private final CommonProps commonProps;

    @Override
    public void sendInvitationMail(UserDto userDto, MeetingDto meetingDto) {
        var context = prepareContext(userDto, meetingDto);
        var html = templateEngine.process("invitationMeeting", context);
        sendMail(userDto.getEmail(),
                MailSubject.INVITATION.toString(),
                html);
    }

    @Override
    public void sendChangeMeetingMail(UserDto userDto, MeetingDto meetingDto) {
        var context = prepareContext(userDto, meetingDto);
        var html = templateEngine.process("changedMeeting", context);
        sendMail(userDto.getEmail(),
                MailSubject.CHANGE_MEETING.toString(),
                html);
    }

    @Override
    public void senCancelMeetingMail(UserDto userDto, MeetingDto meetingDto) {
        var context = prepareContext(userDto, meetingDto);
        var html = templateEngine.process("canceledMeeting", context);
        sendMail(userDto.getEmail(),
                MailSubject.CANCEL_MEETING.toString(),
                html);
    }

    private void sendMail(String to, String subject, String text) {
        try {
            var message = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MailSenderException(e.getMessage());
        }
    }

    private Context prepareContext(UserDto userDto, MeetingDto meetingDto) {
        var context = new Context();
        context.setVariable("name", userDto.getName());
        context.setVariable("surname", userDto.getSurname());
        context.setVariable("direction", meetingDto.getDirectionDto().getName());
        context.setVariable("description", meetingDto.getDescription());
        context.setVariable("date", meetingDto.getStartDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        context.setVariable("time", meetingDto.getStartDateTime().format(DateTimeFormatter.ofPattern("hh-mm")));
        context.setVariable("duration", meetingDto.getDurationMin());
        context.setVariable("refs", meetingDto.getRef());
        context.setVariable("meeting_id", meetingDto.getId());
        context.setVariable("user_id", userDto.getId());
        context.setVariable("url", commonProps.getUrl());
        return context;
    }
}
