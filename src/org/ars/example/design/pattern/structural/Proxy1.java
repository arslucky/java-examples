package org.ars.example.design.pattern.structural;

import static java.lang.Thread.sleep;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * used in {@link java.lang.reflect.Proxy}, {@link javax.ejb.EJB}, {@link javax.inject.Inject}, {@link javax.persistence.PersistenceContext}
 */
public class Proxy1 {

    static Logger log = LogManager.getLogger( Proxy1.class);

    static Map<Integer, String> TABLE = new HashMap<>();

    static {
        TABLE.put( 0, "zero");
        TABLE.put( 1, "one");
        TABLE.put( 2, "two");
        TABLE.put( 3, "three");
        TABLE.put( 4, "four");
        TABLE.put( 5, "five");
    }

    static interface DbService {
        String get( Integer key) throws InterruptedException;
    }

    static class DbClient implements DbService {

        @Override
        public String get( Integer key) throws InterruptedException {
            sleep( 2000); // emulate db connect
            log.info( "table::key:{}::value:{}", key, TABLE.get( key));
            return TABLE.get( key);
        }

    }

    static class DbClientProxy implements DbService {

        DbClient dbClient = null;

        static Map<Integer, String> cache = new HashMap<>();

        DbClientProxy() {
            this.dbClient = new DbClient();
        }

        @Override
        public String get( Integer key) throws InterruptedException {
            if( cache.containsKey( key)) {
                log.info( "cache::key:{}::value:{}", key, cache.get( key));
                return cache.get( key);
            } else {
                cache.put( key, this.dbClient.get( key));
            }
            return cache.get( key);
        }
    }

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            DbService dbService = new DbClientProxy();

            for( int i = 0; i < 50; i++) {
                dbService.get( (int) (Math.random() * 6));
            }

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
