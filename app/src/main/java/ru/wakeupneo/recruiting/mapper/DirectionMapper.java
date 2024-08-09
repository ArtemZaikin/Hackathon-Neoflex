package ru.wakeupneo.recruiting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.wakeupneo.recruiting.dto.DirectionDto;
import ru.wakeupneo.recruiting.model.Direction;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DirectionMapper {

    Direction toDirection(DirectionDto directionDto);

    DirectionDto toDirectionDto(Direction direction);
}
