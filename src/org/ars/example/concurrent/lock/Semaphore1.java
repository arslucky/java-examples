package org.ars.example.concurrent.lock;

import static java.lang.Thread.sleep;

import java.util.concurrent.Semaphore;

public class Semaphore1 {

    public static void main( String[] args) {
        try {
            Semaphore semaphore = new Semaphore( 1);

            Thread threadGet = new Thread( () -> {
                for( int i = 0; i < 10; i++) {
                    try {
                        semaphore.release();
                        System.out.println( "get:" + i);
                        sleep( 100);
                    } catch( Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println( "remove:finsih");
            });
            Thread threadInsert = new Thread( () -> {
                for( int i = 0; i < 10; i++) {
                    try {
                        semaphore.acquire();
                        System.out.println( "insert:" + i);
                        sleep( 50);
                    } catch( InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println( "insert:finsih");
            });

            threadGet.start();
            threadInsert.start();
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
