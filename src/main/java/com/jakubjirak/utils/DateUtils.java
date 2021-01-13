package com.jakubjirak.utils;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {
    public List<LocalDate> getDatesWithoutWeekDates(final LocalDate start, final LocalDate end){
        List<LocalDate> dates = new ArrayList<>();

        LocalDate weekday = start;
        if (start.getDayOfWeek() == DateTimeConstants.SATURDAY|| start.getDayOfWeek() == DateTimeConstants.SUNDAY) {
            weekday = weekday.plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY);
        }

        while (weekday.isBefore(end)) {
            System.out.println(weekday);
            dates.add(weekday);
            if (weekday.getDayOfWeek() == DateTimeConstants.FRIDAY)
                weekday = weekday.plusDays(3);
            else
                weekday = weekday.plusDays(1);
        }

        return dates;
    }
}
