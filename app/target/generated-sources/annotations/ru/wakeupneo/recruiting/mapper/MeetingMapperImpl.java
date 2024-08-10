package ru.wakeupneo.recruiting.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.wakeupneo.recruiting.dto.MeetingDto;
import ru.wakeupneo.recruiting.dto.UserDto;
import ru.wakeupneo.recruiting.model.Meeting;
import ru.wakeupneo.recruiting.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-11T00:21:02+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class MeetingMapperImpl implements MeetingMapper {

    private final UserMapper userMapper;
    private final DirectionMapper directionMapper;

    @Autowired
    public MeetingMapperImpl(UserMapper userMapper, DirectionMapper directionMapper) {

        this.userMapper = userMapper;
        this.directionMapper = directionMapper;
    }

    @Override
    public Meeting toMeeting(MeetingDto meetingDto) {
        if ( meetingDto == null ) {
            return null;
        }

        Meeting meeting = new Meeting();

        meeting.setDirection( directionMapper.toDirection( meetingDto.getDirectionDto() ) );
        meeting.setId( meetingDto.getId() );
        meeting.setName( meetingDto.getName() );
        meeting.setDescription( meetingDto.getDescription() );
        meeting.setMeetingStatus( meetingDto.getMeetingStatus() );
        meeting.setStartDateTime( meetingDto.getStartDateTime() );
        meeting.setDurationMin( meetingDto.getDurationMin() );
        meeting.setRef( meetingDto.getRef() );
        meeting.setParticipants( userDtoListToUserList( meetingDto.getParticipants() ) );

        return meeting;
    }

    @Override
    public MeetingDto toMeetingDto(Meeting meeting) {
        if ( meeting == null ) {
            return null;
        }

        MeetingDto meetingDto = new MeetingDto();

        meetingDto.setDirectionDto( directionMapper.toDirectionDto( meeting.getDirection() ) );
        meetingDto.setId( meeting.getId() );
        meetingDto.setName( meeting.getName() );
        meetingDto.setDescription( meeting.getDescription() );
        meetingDto.setMeetingStatus( meeting.getMeetingStatus() );
        meetingDto.setStartDateTime( meeting.getStartDateTime() );
        meetingDto.setDurationMin( meeting.getDurationMin() );
        meetingDto.setRef( meeting.getRef() );
        meetingDto.setParticipants( userListToUserDtoList( meeting.getParticipants() ) );

        return meetingDto;
    }

    protected List<User> userDtoListToUserList(List<UserDto> list) {
        if ( list == null ) {
            return null;
        }

        List<User> list1 = new ArrayList<User>( list.size() );
        for ( UserDto userDto : list ) {
            list1.add( userMapper.toUser( userDto ) );
        }

        return list1;
    }

    protected List<UserDto> userListToUserDtoList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserDto> list1 = new ArrayList<UserDto>( list.size() );
        for ( User user : list ) {
            list1.add( userMapper.toUserDto( user ) );
        }

        return list1;
    }
}
