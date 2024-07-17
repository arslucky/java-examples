package org.ars.example.function;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.function.Predicate;

@Log4j2
public class Predicate1 {
    public static void main(String[] args) {
        try {
            Predicate<Integer> p = i -> i == 1;
            var l = List.of(1,2,3,4,5);
            log.info(l.stream().anyMatch(p.or(i -> i == 3).or(i -> i < 100)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
