package org.ars.example.concurrent.lock;

import static java.lang.Thread.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Not resolved dead lock
public class DeadLock1 {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main( String[] args) {
        try {
            Thread thread1 = new Thread( () -> {
                try {
                    System.out.println( Thread.currentThread().getName() + ":lock1:start");
                    lock1.lock();
                    sleep( 1000);
                    System.out.println( Thread.currentThread().getName() + ":lock2:getting");
                    lock2.lock();
                } catch( Exception e) {
                    System.out.println( e);
                } finally {
                    System.out.println( Thread.currentThread().getName() + ":unlock");
                    lock2.unlock();
                    lock1.unlock();
                }
            });

            Thread thread2 = new Thread( () -> {
                try {
                    System.out.println( Thread.currentThread().getName() + ":lock2:start");
                    lock2.lock();
                    sleep( 1000);
                    System.out.println( Thread.currentThread().getName() + ":lock1:getting");
                    lock1.lock();
                } catch( Exception e) {
                    System.out.println( e);
                } finally {
                    System.out.println( Thread.currentThread().getName() + ":unlock");
                    lock2.unlock();
                    lock1.unlock();
                }
            });

            thread1.start();
            thread2.start();

        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
