package org.ars.example.concurrent;

public class ObjectNotify1 {

    void display() {
        synchronized( this) {
            System.out.println( Thread.currentThread().getName() + ":wait:start");
            try {
                this.wait(); // release lock
                // get lock again
                System.out.println( Thread.currentThread().getName() + ":wait:notified");
            } catch( InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main( String[] args) {
        try {
            ObjectNotify1 objectNotify = new ObjectNotify1();

            Thread thread1 = new Thread( () -> {
                objectNotify.display();
            });

            thread1.start();

            Thread.sleep( 2000);
            synchronized( objectNotify) {
                System.err.println( Thread.currentThread().getName() + ":notify");
                objectNotify.notify();
                Thread.sleep( 2000);
                System.err.println( Thread.currentThread().getName() + ":release lock");
            }
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
