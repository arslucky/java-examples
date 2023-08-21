package org.ars.example.stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * Sorted - statefull operation, current element processing depended from boundary elements
 */
public class Sorted1 {

    static Logger log = LogManager.getLogger( Sorted1.class);

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            Person.persons.stream()
                    .sorted( ( a, b) -> a.getName().compareTo( b.getName())).forEach( log::info);
        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
