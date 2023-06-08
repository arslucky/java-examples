package org.ars.example.thread;

public class CallRun1 {
    public void run() {
        System.out.println( Thread.currentThread().getName() + ":task");
    }

    public static void main( String args[]) {
        System.out.println( Thread.currentThread().getName() + ":main");
        CallRun1 t1 = new CallRun1();
        t1.run();// fine, but does not start a separate call stack
    }
}
