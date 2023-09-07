package org.ars.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 */
public class FlatMap1 {
    static Logger log = LogManager.getLogger( FlatMap1.class);

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            List<Integer> list = Arrays.asList( 1, 10, 20, 30);

            List<Integer> res = list.stream().flatMap( e -> Stream.of( e, e + 1)).collect( Collectors.toList());

            res.forEach( log::info);
        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
