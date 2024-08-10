package ru.wakeupneo.recruiting.mapper;

import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.wakeupneo.recruiting.dto.UserDto;
import ru.wakeupneo.recruiting.model.TimeSlot;
import ru.wakeupneo.recruiting.model.User;
import ru.wakeupneo.recruiting.model.enums.UserCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-11T00:21:02+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.name( userDto.getName() );
        user.surname( userDto.getSurname() );
        user.email( userDto.getEmail() );
        user.phoneNumber( userDto.getPhoneNumber() );
        user.category( userDto.getCategory() );

        return user.build();
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String surname = null;
        String email = null;
        String phoneNumber = null;
        UserCategory category = null;

        id = user.getId();
        name = user.getName();
        surname = user.getSurname();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        category = user.getCategory();

        String timeZone = null;
        List<List<TimeSlot>> freeTimeList = null;

        UserDto userDto = new UserDto( id, name, surname, email, phoneNumber, timeZone, category, freeTimeList );

        return userDto;
    }
}
