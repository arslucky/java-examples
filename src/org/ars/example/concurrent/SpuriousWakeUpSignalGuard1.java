package org.ars.example.concurrent;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

//to fix JVM internal issue when exists wait() without getting any notify() call
public class SpuriousWakeUpSignalGuard1 {

    Object monitor = new Object();
    boolean wasSignaled;

    void doNotify() {
        synchronized( monitor) {
            wasSignaled = true;
            monitor.notify();
            System.out.println( currentThread().getName() + ":notify");
            try {
                sleep( 1000);
            } catch( InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void doWait() {
        synchronized( monitor) {
            while( !wasSignaled) {
                try {
                    System.out.println( currentThread().getName() + ":waiting");
                    monitor.wait();
                    System.out.println( currentThread().getName() + ":got signal");
                } catch( InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main( String[] args) {
        try {
            SpuriousWakeUpSignalGuard1 obj = new SpuriousWakeUpSignalGuard1();

            Thread thread1 = new Thread( () -> {
                obj.doWait();
            });

            Thread thread2 = new Thread( () -> {
                obj.doNotify();
            });

            thread1.start();
            thread2.start();

        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
