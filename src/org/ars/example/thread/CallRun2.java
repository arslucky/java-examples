package org.ars.example.thread;

//Problem if you direct call run() method
public class CallRun2 extends Thread {
    @Override
    public void run() {
        for( int i = 1; i < 5; i++) {
            try {
                Thread.sleep( 500);
            } catch( InterruptedException e) {
                System.out.println( Thread.currentThread().getName() + " :" + e);
            }
            System.out.println( Thread.currentThread().getName() + " :" + i);
        }
    }

    public static void main( String args[]) {
        System.out.println( Thread.currentThread().getName() + ":main");

        CallRun2 t1 = new CallRun2();
        CallRun2 t2 = new CallRun2();

        t1.run(); // run in the same call stack
        t2.run(); //
    }

}
