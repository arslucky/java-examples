package org.ars.example.function;

import lombok.extern.log4j.Log4j2;

import java.util.function.BinaryOperator;

@Log4j2
public class BinaryOperator1 {
    public static void main(String[] args) {
        try {
            BinaryOperator<Integer> bo = Integer::sum;
            log.info("sum: {}", bo.apply(1,2));
            var boMax = BinaryOperator.maxBy(Integer::compare);
            log.info("max: {}", boMax.apply(1,2));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
