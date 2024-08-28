package ru.wakeupneo.recruiting.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="recruiting")
@Data
public class CommonProps {
    /**
     * встреча отменится если она не подтверждена и осталось меньше времени чем timeBeforeCancelMeetingMin
     */
    private Integer timeBeforeCancelMeetingMin = 60;
    /**
     * #  minTimeBeforeCreateMeetingMin - минимальное время до создания встречи
     */
    private Integer minTimeBeforeCreateMeetingMin = 60;

}