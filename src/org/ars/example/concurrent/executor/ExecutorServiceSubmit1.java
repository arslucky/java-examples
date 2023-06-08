package org.ars.example.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Thread return Feature for Runnable task
public class ExecutorServiceSubmit1 {

    static class TaskRun implements Runnable {
        @Override
        public void run() {
            //@formatter:off
            try {Thread.sleep( 1000);} catch( InterruptedException e) {e.printStackTrace();}//@formatter:on
            System.out.println( Thread.currentThread().getName() + ": task");
        }

    }

    public static void main( String[] args) {
        ExecutorService executorService = null;
        try {
            System.out.println( "main:start");
            executorService = Executors.newFixedThreadPool( 2);
            Future<?> res1 = executorService.submit( new TaskRun());
            Future<?> res2 = executorService.submit( new TaskRun());
            Future<?> res3 = executorService.submit( new TaskRun());

            // returns null if the task has finished correctly.
            System.out.println( "res1.get():" + res1.get()); // Waits if necessary for the computation to complete, and then retrieves its result
            System.out.println( "res1.isDone():" + res1.isDone());
            System.out.println( "res2.isDone():" + res2.isDone());
            System.out.println( "res3.isDone():" + res3.isDone());
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
