package org.ars.example.thread;

public class InterruptingThread2 extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep( 1000);
            System.out.println( "task");
        } catch( InterruptedException e) {
            System.out.println( "Exception handled " + e);
        }
        System.out.println( "thread is running...");
    }

    public static void main( String args[]) {
        InterruptingThread2 t1 = new InterruptingThread2();
        t1.start();
        t1.interrupt();
    }
}