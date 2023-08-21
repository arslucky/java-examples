package org.ars.example.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 */
public class NonInterference1 {

    static Logger log = LogManager.getLogger( NonInterference1.class);

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            List<String> list = new ArrayList<String>( Arrays.asList( "one", "two"));
            Stream<String> stream = list.stream();
            list.add( "third"); // stream source updated before calling a terminal operation, it's ok
            stream.forEach( log::info);
        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
