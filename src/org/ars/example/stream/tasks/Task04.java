package org.ars.example.stream.tasks;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * Write a Java program to calculate the sum of all even, odd numbers in a list using streams.
 */
public class Task04 {
    static Logger log = LogManager.getLogger( Task04.class);

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            List<Integer> list = Arrays.asList( 1, 2, 3, 4, 5);

            int sumAll = list.stream().mapToInt( Integer::intValue).sum();
            int sumEven = list.stream().filter( e -> e % 2 == 0).mapToInt( Integer::intValue).sum();
            int sumOdd = list.stream().filter( e -> e % 2 != 0).mapToInt( Integer::intValue).sum();

            log.info( "sumAll:{}, sumEven:{}, sumOdd:{}", sumAll, sumEven, sumOdd);

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
