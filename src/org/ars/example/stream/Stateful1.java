package org.ars.example.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * Result is unpredictable in parallel for stateful non-synchronized operations
 *
 * 18:47:44 [INFO] org.ars.exa.str.Stateful1 - main:start
 * 18:47:44 [INFO] org.ars.exa.str.Stateful1 - list.size: 1000000, listNotSynch.size:873109, listSynch.size:1000000
 * 18:47:44 [INFO] org.ars.exa.str.Stateful1 - list.size: 1000000, listNotSynch.size:878417, listSynch.size:1000000
 * 18:47:44 [INFO] org.ars.exa.str.Stateful1 - main:finish
 *
 */
public class Stateful1 {

    static Logger log = LogManager.getLogger( Stateful1.class);

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            List<Integer> list = new ArrayList<>( 1_000_000);
            for( int i = 0; i < 1_000_000; i++) {
                list.add( i);
            }

            for( int i = 0; i < 2; i++) {
                List<Integer> listNotSynch = new ArrayList<>();
                List<Integer> listSynch = Collections.synchronizedList( new ArrayList<>());

                Stream<Integer> stream = list.stream();

                stream.parallel().forEach( e -> {
                    listNotSynch.add( e); // side-effect
                    listSynch.add( e); // side-effect
                });

                log.info( "list.size: {}, listNotSynch.size:{}, listSynch.size:{}", list.size(), listNotSynch.size(), listSynch.size());
            }
        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
