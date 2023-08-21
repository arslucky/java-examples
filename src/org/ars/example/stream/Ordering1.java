package org.ars.example.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * Differ output order for list and set
 */
public class Ordering1 {

    static Logger log = LogManager.getLogger( Ordering1.class);

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            List<Integer> arr = Arrays.asList( 5, 4, 3, 2, 1);
            List<Integer> list = new ArrayList<>( arr);
            Set<Integer> set = new HashSet<>( arr);

            for( int i = 0; i < 1; i++) {
                Stream<Integer> stream = list.stream();

                log.info( "forEach output:");
                stream.parallel().forEach( e -> log.info( e));// ignore encounter order

                stream = list.stream();

                String s = stream.parallel().map( e -> String.valueOf( e)).reduce( "", ( res, e) -> res.concat( e));
                log.info( "reduce list output: {}", s);
                // ---------------------------------------------//
                stream = set.stream();

                s = stream.parallel().map( e -> String.valueOf( e)).reduce( "", ( res, e) -> res.concat( e));
                log.info( "reduce set output: {}", s);

            }
        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
