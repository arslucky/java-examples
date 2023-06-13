package org.ars.example.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;

//Optimistic strategy
public class CompareAndSwapLock2 {

    AtomicInteger count = new AtomicInteger();

    void increment( int oldValue) {
        int newValue = oldValue + 1;
        if( count.compareAndSet( oldValue, newValue)) {
            System.out.println( Thread.currentThread().getName() + ": " + newValue);
        }
    }

    public static void main( String[] args) {
        try {
            CompareAndSwapLock2 obj = new CompareAndSwapLock2();

            Thread thread1 = new Thread( () -> {
                for( int i = 0; i < 1_000; i++) {
                    obj.increment( i);
                }
            });

            Thread thread2 = new Thread( () -> {
                for( int i = 0; i < 1_000; i++) {
                    obj.increment( i);
                }
            });

            thread1.start();
            thread2.start();

        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
