package org.ars.example.design.pattern.creational;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * used in {@link java.lang.Runtime#getRuntime}
 *
 */
public class Singleton1 {

    private static Logger log = LogManager.getLogger( Singleton1.class);
    private static volatile Singleton1 instance;

    String value;

    private Singleton1( String value) {
        this.value = value;
    }

    public static Singleton1 getInstance( String value) {
        if( instance != null) {
            return instance;
        }
        synchronized( Singleton1.class) {
            if( instance == null) {
                instance = new Singleton1( value);
            }
            return instance;
        }
    }

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            Thread thread1 = new Thread( () -> {
                log.info( Singleton1.getInstance( "one").value);
            });

            Thread thread2 = new Thread( () -> {
                log.info( Singleton1.getInstance( "two").value);
            });

            thread1.start();
            thread2.start();

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
