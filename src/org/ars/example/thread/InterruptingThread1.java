package org.ars.example.thread;

public class InterruptingThread1 extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep( 1000);
            System.out.println( "task");
        } catch( InterruptedException e) {
            throw new RuntimeException( "Thread interrupted " + e);
        }

    }

    public static void main( String args[]) {
        InterruptingThread1 t1 = new InterruptingThread1();
        t1.start();
        try {
            t1.interrupt();
        } catch( Exception e) {
            System.out.println( "Exception handled " + e); // exceptions are local to a thread, and main thread doesn't actually see the run method
        }
    }

}
