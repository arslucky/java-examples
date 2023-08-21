package org.ars.example.stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 */
public class Filter1 {

    static Logger log = LogManager.getLogger( Filter1.class);

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            Person.persons.stream()
                    .filter( p -> p.getAge() >= 30).forEach( log::info);
        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
