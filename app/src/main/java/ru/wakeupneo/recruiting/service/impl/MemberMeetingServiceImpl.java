package ru.wakeupneo.recruiting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.wakeupneo.recruiting.model.MemberMeeting;
import ru.wakeupneo.recruiting.model.enums.InvitationStatus;
import ru.wakeupneo.recruiting.repository.MemberMeetingRepository;
import ru.wakeupneo.recruiting.service.MemberMeetingService;
import ru.wakeupneo.recruiting.util.exception.MemberMeetingException;

@Service
@RequiredArgsConstructor
public class MemberMeetingServiceImpl implements MemberMeetingService {

    private final MemberMeetingRepository memberMeetingRepository;

    @Override
    public MemberMeeting findByUserIdAndMeetingId(long memberId, long meetingId) {
        var memberMeetingOptional = memberMeetingRepository.findByUserIdAndMeetingId(memberId, meetingId);
        if (memberMeetingOptional.isEmpty()) {
            throw new MemberMeetingException(String.format("Для польователя с id %s не найдено встречи с id %s", memberId, meetingId));
        }
        return memberMeetingOptional.get();
    }

    @Override
    public void updateStatus(InvitationStatus invitationStatus, MemberMeeting memberMeeting) {
        memberMeeting.setInvitationStatus(invitationStatus);
        memberMeetingRepository.save(memberMeeting);
    }
}
