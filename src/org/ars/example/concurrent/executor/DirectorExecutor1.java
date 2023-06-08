package org.ars.example.concurrent.executor;

import java.util.concurrent.Executor;

//Run task in the caller thread
public class DirectorExecutor1 implements Executor {

    @Override
    public void execute( Runnable command) {
        System.out.println( Thread.currentThread().getName() + ":before execution");
        command.run();
        System.out.println( Thread.currentThread().getName() + ":after execution");
    }

    public static void main( String[] args) {
        try {
            Executor executor = new DirectorExecutor1();
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
