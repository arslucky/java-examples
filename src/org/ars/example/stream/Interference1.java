package org.ars.example.stream;

import static java.lang.Thread.sleep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * Example of wrong stream source interference
 */
public class Interference1 {

    static Logger log = LogManager.getLogger( Interference1.class);

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            List<Integer> list = new ArrayList<>( Arrays.asList( 0, 1, 2));

            Thread thread = new Thread( () -> {
                Stream<Integer> stream = list.stream();

                list.add( 3); // stream source updated before running terminal operation, it's ok

                stream.forEach( e -> {
                    log.info( e);
                    try {
                        sleep( 1000);
                    } catch( InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });
            });

            thread.start();
            sleep( 1000);

            list.add( 4); // stream source updated after running terminal operation, it's wrong

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
