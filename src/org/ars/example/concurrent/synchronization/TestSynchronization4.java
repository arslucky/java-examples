package org.ars.example.concurrent.synchronization;

//In this example we have used synchronized keyword on the static method to perform static synchronization.
public class TestSynchronization4 {

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

    static class MyThread1 extends Thread {
        @Override
        public void run() {
            Table.printTable( 1);
        }
    }

    static class MyThread2 extends Thread {
        @Override
        public void run() {
            Table.printTable( 10);
        }
    }

    static class MyThread3 extends Thread {
        @Override
        public void run() {
            Table.printTable( 100);
        }
    }

    static class MyThread4 extends Thread {
        @Override
        public void run() {
            Table.printTable( 1000);
        }
    }

    public static void main( String t[]) {
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();
        MyThread3 t3 = new MyThread3();
        MyThread4 t4 = new MyThread4();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
