package org.ars.example.concurrent.executor;

import java.util.concurrent.Executor;

//Run task in the caller thread
public class DirectorExecutor2 implements Executor {

    @Override
    public void execute( Runnable command) {
        System.out.println( Thread.currentThread().getName() + ":before execution");
        Thread thread = new Thread( command);
        thread.run();
        thread.run();
        System.out.println( Thread.currentThread().getName() + ":after execution");
    }

    public static void main( String[] args) {
        try {
            Executor executor = new DirectorExecutor2();
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
