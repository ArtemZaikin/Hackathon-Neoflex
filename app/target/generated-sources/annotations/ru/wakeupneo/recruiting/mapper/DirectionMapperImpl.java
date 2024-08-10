package ru.wakeupneo.recruiting.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.wakeupneo.recruiting.dto.DirectionDto;
import ru.wakeupneo.recruiting.model.Direction;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-11T00:21:01+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class DirectionMapperImpl implements DirectionMapper {

    @Override
    public Direction toDirection(DirectionDto directionDto) {
        if ( directionDto == null ) {
            return null;
        }

        Direction.DirectionBuilder direction = Direction.builder();

        direction.name( directionDto.getName() );

        return direction.build();
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
