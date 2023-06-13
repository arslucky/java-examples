package org.ars.example.concurrent.lock;

import java.util.concurrent.atomic.AtomicBoolean;

//Boundary strategy
public class CompareAndSwapLock1 {

    AtomicBoolean lockAtomic = new AtomicBoolean( false);
    volatile int count = 0;

    void unlock() {
        lockAtomic.set( false);
    }

    void lock() {
        while( !lockAtomic.compareAndSet( false, true)) {
            // cycling until lock available
        }
    }

    void increment() {
        System.out.println( Thread.currentThread().getName() + ": " + count++);
    }

    public static void main( String[] args) {
        try {
            CompareAndSwapLock1 obj = new CompareAndSwapLock1();

            Thread thread1 = new Thread( () -> {
                for( int i = 0; i < 1_000; i++) {
                    obj.lock(); // sequence order is one by one
                    obj.increment();
                    obj.unlock();
                }
            });

            Thread thread2 = new Thread( () -> {
                for( int i = 0; i < 1_000; i++) {
                    obj.lock(); // sequence order is one by one
                    obj.increment();
                    obj.unlock();
                }
            });

            thread1.start();
            thread2.start();

        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
