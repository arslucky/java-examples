package org.ars.example.stream.tasks;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * Write a Java program to remove all duplicate elements from a list using streams
 */
public class Task05 {

    static Logger log = LogManager.getLogger( Task05.class);

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            List<Integer> list = Arrays.asList( 1, 2, 2, 3, 4, 5, 5, 6);

            log.info( list);

            String s = list.stream().distinct().map( String::valueOf).collect( Collectors.joining( ","));
            log.info( s);
        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
