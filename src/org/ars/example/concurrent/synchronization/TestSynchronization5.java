package org.ars.example.concurrent.synchronization;

//In this example, we are using anonymous class to create the threads.
public class TestSynchronization5 {

    static class Table {

        synchronized static void printTable( int n) {
            for( int i = 1; i <= 10; i++) {
                System.out.println( n * i);
                try {
                    Thread.sleep( 400);
                } catch( Exception e) {
                }
            }
        }
    }

    public static void main( String[] args) {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                Table.printTable( 1);
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                Table.printTable( 10);
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                Table.printTable( 100);
            }
        };

        Thread t4 = new Thread() {
            @Override
            public void run() {
                Table.printTable( 1000);
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
