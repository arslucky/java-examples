package org.ars.example.stream.tasks;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * Write a Java program to convert a list of strings to uppercase or lowercase using streams.
 */
public class Task03 {

    static Logger log = LogManager.getLogger( Task03.class);

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            List<String> list = Arrays.asList( "One", "two", "THREE");
            List<String> res = list.stream().map( String::toUpperCase).collect( Collectors.toList());

            res.stream().forEach( log::info);

            res = list.stream().map( String::toLowerCase).collect( Collectors.toList());

            res.stream().forEach( log::info);

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
