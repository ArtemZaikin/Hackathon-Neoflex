package ru.wakeupneo.recruiting.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.wakeupneo.recruiting.dto.DirectionDto;
import ru.wakeupneo.recruiting.model.Direction;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-11T00:01:20+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class DirectionMapperImpl implements DirectionMapper {

    @Override
    public Direction toDirection(DirectionDto directionDto) {
        if ( directionDto == null ) {
            return null;
        }

        Direction direction = new Direction();

        direction.setName( directionDto.getName() );

        return direction;
    }

    @Override
    public DirectionDto toDirectionDto(Direction direction) {
        if ( direction == null ) {
            return null;
        }

        DirectionDto directionDto = new DirectionDto();

        directionDto.setName( direction.getName() );

        return directionDto;
    }
}
