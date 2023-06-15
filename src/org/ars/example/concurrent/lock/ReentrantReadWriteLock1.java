package org.ars.example.concurrent.lock;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLock1 {

    static class Counter {
        int counter = 0;

        int get() {
            return counter;
        }

        int increment() {
            return ++counter;
        }
    }

    public static void main( String[] args) {
        try {
            Counter counter = new Counter();
            ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

            Thread threadRead = new Thread( () -> {
                for( int i = 0; i < 20; i++) {
                    try {
                        lock.readLock().lock();
                        System.out.println( currentThread().getName() + ":read:" + counter.get());
                        sleep( 100);
                    } catch( InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.readLock().unlock();
                    }
                }
            });

            Thread threadWrite = new Thread( () -> {
                for( int i = 0; i < 20; i++) {
                    try {
                        lock.writeLock().lock();
                        System.out.println( currentThread().getName() + ":write:" + counter.increment());
                        sleep( 200);
                    } catch( InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.writeLock().unlock();
                    }
                }
            });

            threadRead.start();
            threadWrite.start();

        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
