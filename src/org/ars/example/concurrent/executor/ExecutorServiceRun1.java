package org.ars.example.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//There is no way of obtaining the result of the executed Runnable, if necessary
public class ExecutorServiceRun1 {

    static class TaskRun implements Runnable {
        @Override
        public void run() {
            //@formatter:off
            try {Thread.sleep( 500);} catch( InterruptedException e) {e.printStackTrace();}//@formatter:on
            System.out.println( Thread.currentThread().getName() + ": task");
        }

    }

    public static void main( String[] args) {
        ExecutorService executorService = null;
        try {
            System.out.println( "main:start");
            executorService = Executors.newFixedThreadPool( 2);
            executorService.execute( new TaskRun());
            executorService.execute( new TaskRun());
            executorService.execute( new TaskRun());
            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( e);
        } finally {
            if( executorService != null) {
                executorService.shutdown();
            }
        }
    }
}
