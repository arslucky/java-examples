package org.ars.example.time;

import lombok.extern.log4j.Log4j2;

import java.time.temporal.ChronoUnit;

@Log4j2
public class ChronoUnit01 {
    public static void main(String[] args) {
        try {
            log.info("Second: {}", ChronoUnit.SECONDS.getDuration().getSeconds());
            log.info("Second::isDurationEstimated: {}", ChronoUnit.SECONDS.isDurationEstimated());

            log.info("Minute: {}", ChronoUnit.MINUTES.getDuration().getSeconds());
            log.info("Minute::isDurationEstimated: {}", ChronoUnit.MINUTES.isDurationEstimated());

            log.info("Hour: {}", ChronoUnit.SECONDS.getDuration().getSeconds());
            log.info("Hour::isDurationEstimated: {}", ChronoUnit.SECONDS.isDurationEstimated());

            //estimated value
            log.info("Month: {}", ChronoUnit.MONTHS.getDuration().getSeconds());
            log.info("Month::isDurationEstimated: {}", ChronoUnit.MONTHS.isDurationEstimated());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
