package org.ars.example.concurrent;

public class ObjectNotifyAll1 {

    void display() {
        synchronized( this) {
            System.out.println( Thread.currentThread().getName() + ":wait:start");
            try {
                this.wait(); // release lock
                // get lock again
                System.out.println( Thread.currentThread().getName() + ":wait:notified");
                Thread.sleep( 5000);
            } catch( InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main( String[] args) {
        try {
            ObjectNotifyAll1 objectNotifyAll = new ObjectNotifyAll1();

            Thread thread1 = new Thread( () -> {
                objectNotifyAll.display();
            });
            Thread thread2 = new Thread( () -> {
                objectNotifyAll.display();
            });

            thread1.start();
            thread2.start();

            Thread.sleep( 2000);
            synchronized( objectNotifyAll) {
                System.out.println( Thread.currentThread().getName() + ":notifyAll");
                objectNotifyAll.notifyAll();
                Thread.sleep( 2000);
                System.out.println( Thread.currentThread().getName() + ":release lock");
            }
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
