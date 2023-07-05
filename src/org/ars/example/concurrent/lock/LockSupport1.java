package org.ars.example.concurrent.lock;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import java.util.concurrent.locks.LockSupport;

/**
 * @author arsen.ibragimov
 *
 *         low-level API, no happens-before relationship
 *         {@link LockSupport} is a wrapper of the {@link sun.misc.Unsafe} class
 */
public class LockSupport1 {

    public static void main( String[] args) {
        try {
            System.out.println( currentThread().getName() + ":start");
            Thread thread = new Thread( () -> {
                System.out.println( currentThread().getName() + ":begin");
                LockSupport.park();
                System.out.println( currentThread().getName() + ":end");
            });

            thread.start();
            sleep( 1000);
            System.out.println( currentThread().getName() + ":before unlocking");
            LockSupport.unpark( thread);
            System.out.println( currentThread().getName() + ":end");
        } catch( Exception e) {
            System.err.println( e);
        }

    }
}
