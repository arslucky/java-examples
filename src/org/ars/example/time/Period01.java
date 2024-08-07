package org.ars.example.time;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.Period;

@Log4j2
public class Period01 {
    public static void main(String[] args) {
        try {
            var now = LocalDate.now();
            log.info("period:{}", now.until(now.plusDays(40)));

            log.info("plus 30 days: {}", now.plus(Period.ofDays(30)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
