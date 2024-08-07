package org.ars.example.time;

import lombok.extern.log4j.Log4j2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Log4j2
public class TemporalAdjuster01 {
    public static void main(String[] args) {
        try {
            var now = LocalDate.now();
            log.info("firstDayOfMonth: {}", now.with(TemporalAdjusters.firstDayOfMonth()));
            log.info("lastDayOfMonth: {}", now.with(TemporalAdjusters.lastDayOfMonth()));
            log.info("dayOfWeekInMonth, first Monday: {}", now.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
