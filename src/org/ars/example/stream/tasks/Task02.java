package org.ars.example.stream.tasks;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * Write a Java program to calculate the average of a list of integers using streams.
 */
public class Task02 {

    static Logger log = LogManager.getLogger( Task02.class);

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            List<Integer> list = Arrays.asList( 1, 2, 3, 4, 5, 6);
            double average = list.stream().mapToDouble( Integer::doubleValue).average().orElse( 0);

            log.info( "array:{}, average:{}", list, average);

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
