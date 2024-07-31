package org.ars.example.time;

import lombok.extern.log4j.Log4j2;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Log4j2
public class TemporalUnit01 {
    public static void main(String[] args) {
        try {
            var start = LocalTime.of(1,1, 1);
            var end = LocalTime.of(1,1, 2);
            log.info("NANOS: {}", ChronoUnit.NANOS.between(start, end));
            log.info("SECONDS: {}", ChronoUnit.SECONDS.between(start, end));
            log.info("MINUTES: {}", ChronoUnit.MINUTES.between(start, end));
            log.info("HOURS: {}", ChronoUnit.HOURS.between(start, end));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
