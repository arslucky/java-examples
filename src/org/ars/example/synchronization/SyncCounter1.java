package org.ars.example.synchronization;

public class SyncCounter1 {

    private int counter = 0;

    synchronized void incCount() {
        this.counter++;
    }

    synchronized int getCount() {
        return this.counter;
    }

    public static void main( String[] args) {
        try {
            SyncCounter1 counter = new SyncCounter1();

            Thread thread1 = new Thread( () -> {
                for( int i = 0; i < 1_000_000; i++) {
                    counter.incCount();
                }
                System.out.println( counter.getCount());
            });

            Thread thread2 = new Thread( () -> {
                for( int i = 0; i < 1_000_000; i++) {
                    counter.incCount();
                }
                System.out.println( counter.getCount());
            });

            thread1.start();
            thread2.start();

            // output
            // 1172553
            // 2000000
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
