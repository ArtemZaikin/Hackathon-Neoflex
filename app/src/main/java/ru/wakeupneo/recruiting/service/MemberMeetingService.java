package ru.wakeupneo.recruiting.service;

import ru.wakeupneo.recruiting.model.MemberMeeting;
import ru.wakeupneo.recruiting.model.enums.InvitationStatus;

public interface MemberMeetingService {
    MemberMeeting findByUserIdAndMeetingId(long memberId, long meetingId);

    void updateStatus(InvitationStatus invitationStatus, MemberMeeting memberMeeting);
}
