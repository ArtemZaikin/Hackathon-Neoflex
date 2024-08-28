package ru.wakeupneo.recruiting.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.model.Meeting;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapper.class, DirectionMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MeetingMapper {

    @Mapping(source = "directionDto", target = "direction")
    Meeting toMeeting(MeetingDto meetingDto);

    @Mapping(source = "direction", target = "directionDto")
    MeetingDto toMeetingDto(Meeting meeting);
}
