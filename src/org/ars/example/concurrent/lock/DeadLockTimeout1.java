package org.ars.example.concurrent.lock;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//dead lock resolved by timeout strategy
public class DeadLockTimeout1 implements Runnable {

    ReentrantLock lock1;
    ReentrantLock lock2;

    public DeadLockTimeout1( ReentrantLock lock1, ReentrantLock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        boolean isLock = true;
        int c = 0;
        int lockSucessfully = 0;
        int lockUnSucessfully = 0;
        while( c++ < 1_000_000) {
            try {
                if( !isLock) {
                    lockUnSucessfully++;
                    long millis = (long) (100L * Math.random());
                    sleep( millis);
                }
                if( !lock1.tryLock( 1000, TimeUnit.MILLISECONDS)) {
                    System.err.println( currentThread().getName() + ":failed to lock one");
                    isLock = false;
                    continue;
                }
                if( !lock2.tryLock( 1000, TimeUnit.MILLISECONDS)) {
                    System.err.println( currentThread().getName() + ":failed to lock both");
                    lock1.unlock();
                    isLock = false;
                    continue;
                }
                isLock = true;
                lockSucessfully++;
            } catch( Exception e) {
                System.out.println( e);
            } finally {
                if( lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                    lock1.unlock();
                }
            }
        }
        System.out.println( currentThread().getName() + ":lockSucessfully: " + lockSucessfully + " times");
        System.out.println( currentThread().getName() + ":lockUnSucessfully: " + lockUnSucessfully + " times");
    }

    public static void main( String[] args) {

        try {
            ReentrantLock lock1 = new ReentrantLock();
            ReentrantLock lock2 = new ReentrantLock();

            DeadLockTimeout1 thread1 = new DeadLockTimeout1( lock1, lock2);
            DeadLockTimeout1 thread2 = new DeadLockTimeout1( lock2, lock1);

            new Thread( thread1).start();
            new Thread( thread2).start();
            new Thread( thread1).start();
            new Thread( thread2).start();

        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
