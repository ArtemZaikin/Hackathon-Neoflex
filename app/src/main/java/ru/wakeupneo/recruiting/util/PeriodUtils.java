package ru.wakeupneo.recruiting.util;

import org.springframework.stereotype.Component;
import ru.wakeupneo.recruiting.model.Meeting;
import ru.wakeupneo.recruiting.model.TimeSlot;
import ru.wakeupneo.recruiting.model.User;
import ru.wakeupneo.recruiting.model.UserFreeTime;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.time.ZoneOffset.UTC;

@Component
public class PeriodUtils {
    /**
     * Получение списка слотов свободного времени (ССВ) без учета входящего timeSlot
     * * По каждому ССВ Пользователя вычисляется отношение timeSlot
     * * возможные варианты отношений:
     * * timeSlot не пересекается со ССВ - результат ссв переходит в результирующей лист
     * * timeSlot больше либо равен ССВ - ссв не переходит в результат
     * * timeSlot меньше ССВ и совпадает либо начало, либо конец слота - в результирующий лист попадет новый слот "ССВ - timeSlot"
     * * начало timeSlot раньше начала ССВ, а конец timeSlot попадает в ССВ - новый слот в результат
     * * начало timeSlot попадает в ССВ, а конец timeSlot позже СССВ - новый слот в результат
     * * timeSlot меньше ССВ и НЕ совпадает начало И конец - в результирующий лист попадет два новых слота "ССВ - timeSlot"
     *
     * @param user
     * @param timeSlot
     * @return
     */
    public List<UserFreeTime> getUserFreeTimeWithoutPeriod(User user, TimeSlot timeSlot) {
        if (user.getFreeTimeSlotList() == null) {
            if (user.getFreeTimeSlotList().size() == 0) {
                return new ArrayList<>();
            }
        }
        List<UserFreeTime> result = new ArrayList<>();
        for (UserFreeTime slot : user.getFreeTimeSlotList()) {
            getFirstSlotWithoutSecondSlot(new TimeSlot(slot.getStartDatetime(), slot.getDurationMin()), timeSlot)
                    .forEach(timeSlot1 -> result.add(
                            new UserFreeTime(null, user, timeSlot1.getStartDateTime(), timeSlot.getDurationMin())));
        }
        return result;
    }

    public List<UserFreeTime> getTimePeriodWithoutMeeting(User user) {
        if (user.getMeetings() == null) {
            if (user.getMeetings().size() == 0) {
                return user.getFreeTimeSlotList();
            }
        }
        List<UserFreeTime> result = new ArrayList<>();
        for (Meeting meeting : user.getMeetings()) {
            List<UserFreeTime> userFreeTimeWithoutPeriod = getUserFreeTimeWithoutPeriod(user, new TimeSlot(meeting.getStartDateTime(), meeting.getDurationMin()));
            result.addAll(userFreeTimeWithoutPeriod);
        }
        return result;
    }

    /**
     * Убираем из первого слота второй слот. В результате может получить два новых слота, если второй слот полностью
     * помещается в первый.
     *
     * @param firstTimeSlot
     * @param secondTimeSlot
     * @return возвращает Пустой лист, при пересечении если второй слот совпадает либо больше первого;
     * Лист с первым слотом, если нет пересечения.
     * Лист с двумя новыми слотом, при полном пересечении, если первый слот больше второго;
     * Лист с одним новым слотом, если есть частичное пересечение;
     */
    public List<TimeSlot> getFirstSlotWithoutSecondSlot(TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {

        if (fullOverlappingTimeSlot(secondTimeSlot, firstTimeSlot) || firstTimeSlot.equals(secondTimeSlot)) {
            return new ArrayList<>();
        }
        if (!overlappingTimeSlot(firstTimeSlot, secondTimeSlot)) {
            return new ArrayList<>(List.of(firstTimeSlot));
        }
        if (fullOverlappingTimeSlot(firstTimeSlot, secondTimeSlot)) {
            return new ArrayList<>(List.of(new TimeSlot(firstTimeSlot.getStartDateTime(), secondTimeSlot.getStartDateTime()),
                    new TimeSlot(secondTimeSlot.getEndDateTime(), firstTimeSlot.getEndDateTime())));
        }
        if (overlappingTimeSlot(firstTimeSlot, secondTimeSlot)) {
            if (firstTimeSlot.getStartDateTime().isBefore(secondTimeSlot.getStartDateTime())) {
                return new ArrayList<>(List.of(
                        new TimeSlot(firstTimeSlot.getStartDateTime(), secondTimeSlot.getStartDateTime())));
            } else {
                return new ArrayList<>(List.of(
                        new TimeSlot(secondTimeSlot.getEndDateTime(), firstTimeSlot.getEndDateTime())));
            }
        }
        //todo проверить полноту if
        return new ArrayList<>();
    }


    /**
     * A                            B
     * |-----------------------------|
     * C                                D
     * |--------------------------------|
     * (min(B, D) - max(A, C)) >= 0
     * If this duration is negative, there’s no overlap.
     * If it’s zero, the ranges might be touching, or one might be a single point in time.
     * The application should consider whether to treat this scenario as an overlap or not.
     * Moreover, a positive duration tells us there’s an overlap.
     *
     * @param firstTimeSlot
     * @param secondTimeSlot
     * @return истина если есть пересечение
     */
    public boolean overlappingTimeSlot(TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {
        return (min(firstTimeSlot.getEndDateTime().toEpochSecond(UTC),
                secondTimeSlot.getEndDateTime().toEpochSecond(UTC)) -
                max(firstTimeSlot.getStartDateTime().toEpochSecond(UTC),
                        secondTimeSlot.getStartDateTime().toEpochSecond(UTC))) >= 0;
    }

    /**
     * Расчитывает полное покрытие диапазонов
     *
     * @param firstTimeSlot
     * @param secondTimeSlot
     * @return Возвращает true если secondTimeSlot начинается позже начала firstTimeSlot
     * и заканчивается раньше чем конец firstTimeSlot
     */
    public boolean fullOverlappingTimeSlot(TimeSlot firstTimeSlot, TimeSlot secondTimeSlot) {
        return firstTimeSlot.getStartDateTime().isBefore(secondTimeSlot.getStartDateTime()) &&
                firstTimeSlot.getEndDateTime().isAfter(secondTimeSlot.getEndDateTime());
    }
}
