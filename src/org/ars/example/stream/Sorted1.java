package org.ars.example.stream;

import lombok.extern.log4j.Log4j2;

import java.util.Comparator;

/**
 * @author arsen.ibragimov
 *
 * Sorted - statefull operation, current element processing depended from boundary elements
 */
@Log4j2
public class Sorted1 {
    public static void main( String[] args) {
        try {
            log.info( "main:start");

            Person.persons.stream()
                    .sorted(Comparator.comparing(Person::getName).thenComparing(Person::getAge)).forEach( log::info);
        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
