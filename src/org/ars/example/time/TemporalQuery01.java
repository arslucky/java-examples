package org.ars.example.time;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.time.temporal.TemporalQueries;

@Log4j2
public class TemporalQuery01 {
    public static void main(String[] args) {
        try {
            var now = LocalDateTime.now();

            log.info(now.query(TemporalQueries.localTime()));
            log.info(now.toLocalTime());

            log.info(now.query(TemporalQueries.chronology()));
            log.info(now.getChronology());

            log.info(now.query(TemporalQueries.precision()));
            log.info(now.query(TemporalQueries.zone()));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
