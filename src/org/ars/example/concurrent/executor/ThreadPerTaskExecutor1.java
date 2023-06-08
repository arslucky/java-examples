package org.ars.example.concurrent.executor;

import java.util.concurrent.Executor;

//Run task  in a new thread
public class ThreadPerTaskExecutor1 implements Executor {

    @Override
    public void execute( Runnable command) {
        System.out.println( Thread.currentThread().getName() + ":before running task");
        Thread thread = new Thread( command);
        thread.start();
        // thread.start(); //java.lang.IllegalThreadStateException
        System.out.println( Thread.currentThread().getName() + ":after running task");
    }

    public static void main( String[] args) {
        try {
            Executor executor = new ThreadPerTaskExecutor1();
            executor.execute( new Runnable() {

                @Override
                public void run() {
                    //@formatter:off
                    try {Thread.sleep( 1000);} catch( InterruptedException e) {e.printStackTrace();}//@formatter:on
                    System.out.println( Thread.currentThread().getName() + ":task");
                }
            });

        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
