package org.ars.example.concurrent.lock;

import static java.lang.Thread.sleep;

import java.util.concurrent.Semaphore;

public class Semaphore2 {

    public static void main( String[] args) {
        try {
            Semaphore semaphore = new Semaphore( 0);

            Thread threadGet = new Thread( () -> {
                for( int i = 0; i < 10; i++) {
                    try {
                        semaphore.release( 2);
                        System.out.println( String.format( "get:%d, release:%d", i, 2));
                        sleep( 50);
                    } catch( Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println( "get:finsih");
            });
            Thread threadInsert = new Thread( () -> {
                for( int i = 0; i < 10; i++) {
                    try {
                        semaphore.acquire( 1);
                        System.out.println( String.format( "insert:%d, acquire:%d", i, 1));
                    } catch( InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println( "insert:finsih");
            });

            threadGet.start();
            System.out.println( "semaphore.availablePermits():before:" + semaphore.availablePermits());
            threadInsert.start();
            sleep( 1000);
            System.out.println( "semaphore.availablePermits():after:" + semaphore.availablePermits());
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
