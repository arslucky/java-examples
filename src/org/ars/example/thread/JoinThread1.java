package org.ars.example.thread;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class JoinThread1 {

    public static void main( String[] args) {
        try {
            Thread thread1 = new Thread( () -> {
                try {
                    System.out.println( currentThread().getName() + ":start");
                    sleep( 1000);
                    System.out.println( currentThread().getName() + ":stop");
                } catch( InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread thread2 = new Thread( () -> {
                try {
                    System.out.println( currentThread().getName() + ":start");
                    sleep( 100);
                    System.out.println( currentThread().getName() + ":join");
                    thread1.join();
                    System.out.println( currentThread().getName() + ":stop");
                } catch( InterruptedException e) {
                    e.printStackTrace();
                }
            });

            thread1.start();
            thread2.start();

        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
