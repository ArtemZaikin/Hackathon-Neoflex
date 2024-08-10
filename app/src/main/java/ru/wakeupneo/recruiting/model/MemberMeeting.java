package ru.wakeupneo.recruiting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.wakeupneo.recruiting.model.enums.InvitationStatus;

import javax.persistence.*;

@Entity
@Table(name = "members_meetings")
@IdClass(MemberMeetingId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberMeeting {

    @Id
    @Column(name = "id_person")
    private long userId;

    @Id
    @Column(name = "id_meeting")
    private long meetingId;

    @Column(name = "invitation_status")
    @Enumerated(EnumType.STRING)
    private InvitationStatus invitationStatus;
}
