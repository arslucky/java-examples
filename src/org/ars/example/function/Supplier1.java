package org.ars.example.function;

import lombok.extern.log4j.Log4j2;

import java.util.function.Supplier;

@Log4j2
public class Supplier1 {
    public static void main(String[] args) {
        try {
            Supplier<String> s = () -> "ok";
            log.info(s.get());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
