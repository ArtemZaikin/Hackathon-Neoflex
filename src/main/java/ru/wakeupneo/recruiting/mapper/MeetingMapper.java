package ru.wakeupneo.recruiting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.model.Meeting;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MeetingMapper {

    Meeting toMeeting(MeetingDto meetingDto);

    MeetingDto toMeetingDto(Meeting meeting);
}
