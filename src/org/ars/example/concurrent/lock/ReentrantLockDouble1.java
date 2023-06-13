package org.ars.example.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDouble1 {

    Lock lock = new ReentrantLock();

    private int i;

    int increement() {
        try {
            lock.lock();
            i++;
        } catch( Exception e) {
            System.out.println( e);
        } finally {
            lock.unlock();
        }
        return i;
    }

    int decreement() {
        try {
            lock.lock();
            i--;
        } catch( Exception e) {
            System.out.println( e);
        } finally {
            lock.unlock();
        }
        return i;
    }

    void calculation() {
        try {
            lock.lock();
            for( int i = 0; i < 10; i++) {
                Thread.sleep( 300);
                System.out.println( Thread.currentThread().getName() + "incremented: " + increement());
            }
        } catch( Exception e) {
            System.out.println( e);
        } finally {
            lock.unlock();
        }
    }

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");

            ReentrantLockDouble1 obj = new ReentrantLockDouble1();

            Thread thread1 = new Thread( () -> {
                obj.calculation();
            });

            Thread thread2 = new Thread( () -> {
                try {
                    Thread.sleep( 100);
                    System.out.println( Thread.currentThread().getName() + "decremented: " + obj.decreement());
                } catch( InterruptedException e) {
                    e.printStackTrace();
                }
            });

            thread1.start();
            thread2.start();

            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
