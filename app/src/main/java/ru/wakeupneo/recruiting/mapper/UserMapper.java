package ru.wakeupneo.recruiting.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.wakeupneo.recruiting.dto.UserDto;
import ru.wakeupneo.recruiting.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}
