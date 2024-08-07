package org.ars.example.time;

import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.LocalDateTime;

@Log4j2
public class Duration01 {
    public static void main(String[] args) {
        try {
            var now = LocalDateTime.now();
            log.info("now: {}, plus 9 seconds: {}", now, now.plus(Duration.ofSeconds(9)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
