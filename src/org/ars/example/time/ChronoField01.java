package org.ars.example.time;

import lombok.extern.log4j.Log4j2;

import java.time.temporal.ChronoField;

@Log4j2
public class ChronoField01 {
    public static void main(String[] args) {
        try {
            ChronoField secondOfMinute = ChronoField.SECOND_OF_MINUTE;

            log.info("baseUnit: {}", secondOfMinute.getBaseUnit());
            log.info("rangeUnit: {}", secondOfMinute.getRangeUnit());
            log.info("range: {}", secondOfMinute.range());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
