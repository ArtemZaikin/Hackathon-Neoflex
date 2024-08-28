package ru.wakeupneo.recruiting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberMeetingId implements Serializable {

    private long userId;
    private long meetingId;
}
