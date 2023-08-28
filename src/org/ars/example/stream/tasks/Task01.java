package org.ars.example.stream.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 */
public class Task01 {

    static Logger log = LogManager.getLogger( Task01.class);

    /**
     * Input: RAW_DATA array
     * Task: output to console RAW_DATA, transforming before to:
     * - remove duplicates by id and name)
     * - group by name
     * - sort groups by name
     * - inside group sort by id
     */
    static class Person {
        final Integer id;

        final String name;

        Person( int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int hashCode() {
            return Objects.hash( id, name);
        }

        @Override
        public boolean equals( Object obj) {
            if( this == obj)
                return true;
            if( obj == null)
                return false;
            if( getClass() != obj.getClass())
                return false;
            Person other = (Person) obj;
            return id == other.id && Objects.equals( name, other.name);
        }
    }

    private static Person[] RAW_DATA = new Person[] { new Person( 1, "Harry"), // namesake
            new Person( 0, "Harry"), // duplicate
            new Person( 7, "Amelia"), new Person( 4, "Jack"), new Person( 2, "Harry"), new Person( 5, "Amelia"), new Person( 3, "Emily"), new Person( 0, "Harry"), new Person( 4, "Jack"),
            new Person( 5, "Amelia"), new Person( 6, "Amelia"), new Person( 8, "Amelia"), };

    /*
     * Raw data:
     *
     * 0 - Harry
     * 0 - Harry
     * 1 - Harry
     * 2 - Harry
     * 3 - Emily
     * 4 - Jack
     * 4 - Jack
     * 5 - Amelia
     * 5 - Amelia
     * 6 - Amelia
     * 7 - Amelia
     * 8 - Amelia
     **************************************************
     *
     *
     * Duplicate filtered, grouped by name, sorted by name and id:
     *
     * Amelia:
     * 1 - Amelia (5)
     * 2 - Amelia (6)
     * 3 - Amelia (7)
     * 4 - Amelia (8)
     * Emily:
     * 1 - Emily (3)
     * Harry:
     * 1 - Harry (0)
     * 2 - Harry (1)
     * 3 - Harry (2)
     * Jack:
     * 1 - Jack (4)
     */

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            log.info( "********* Solution 1 *********");

            Collector<Person, ?, Map<String, List<Person>>> collector = Collector.of( TreeMap::new, ( m, p) -> {
                List<Person> list = m.get( p.getName());
                if( list == null) {
                    list = new ArrayList<>();
                }
                list.add( p);
                m.put( p.getName(), list);
            }, ( left, right) -> {
                left.putAll( right);
                return left;
            });

            Map<String, List<Person>> sortedMap = Arrays.stream( RAW_DATA).distinct().collect( collector);

            sortedMap.forEach( ( k, v) -> {
                log.info( "{}:", k);
                final AtomicInteger count = new AtomicInteger( 1);
                v.stream().sorted( Comparator.comparing( Person::getId)).forEach( p -> log.info( "{} - {} ({})", count.getAndIncrement(), p.getName(), p.getId()));
            });

            log.info( "********* Solution 2 *********");

            Map<String, List<Person>> sortedMap1 = Arrays.stream( RAW_DATA).distinct().collect( Collectors.groupingBy( Person::getName, TreeMap::new, Collectors.toList()));

            sortedMap1.forEach( ( k, v) -> {
                log.info( "{}:", k);
                final AtomicInteger count = new AtomicInteger( 1);
                v.stream().sorted( Comparator.comparing( Person::getId)).forEach( p -> log.info( "{} - {} ({})", count.getAndIncrement(), p.getName(), p.getId()));
            });

            log.info( "********* Solution 3 *********");

            sortedMap1 = Arrays.stream( RAW_DATA).distinct().collect( Collectors.groupingBy( Person::getName, TreeMap::new, Collectors.toList()));

            sortedMap1.forEach( ( k, v) -> {
                log.info( "{}:", k);
                Map<Integer, Person> indexedMap = v.stream().sorted( Comparator.comparing( Person::getId)).collect( TreeMap::new, ( map, streamValue) -> map.put( map.size() + 1, streamValue),
                        ( map, map2) -> map.putAll( map2));
                indexedMap.forEach( ( kk, p) -> log.info( "{} - {} ({})", kk, p.getName(), p.getId()));
                });

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
