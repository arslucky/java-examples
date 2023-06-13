package org.ars.example.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock1 {

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");

            Thread thread = new Thread( () -> {
                Lock lock = new ReentrantLock();
                // Thread.currentThread().interrupt();

                try {
                    System.out.println( Thread.currentThread().getName() + ":before getting lock");
                    lock.lockInterruptibly();
                    Thread.sleep( 1000);
                    System.out.println( Thread.currentThread().getName() + ":end task");
                } catch( Exception e) {
                    System.out.println( e);
                } finally {
                    lock.unlock();
                }
            });

            thread.start();
            thread.interrupt();

            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
