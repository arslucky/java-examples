package org.ars.example.concurrent.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

//Thread return Feature for Callable task
public class ExecutorServiceSubmit2 {

    static class TaskRun implements Callable<String> {

        int id;

        public TaskRun( int id) {
            this.id = id;
        }

        @Override
        public String call() throws Exception {
            //@formatter:off
            try {Thread.sleep( 2000);} catch( InterruptedException e) {e.printStackTrace();}//@formatter:on
            System.out.println( String.format( "%s: task %s", Thread.currentThread().getName(), this.id));
            return String.format( "%s: task %s finished", Thread.currentThread().getName(), this.id);
        }

    }

    public static void main( String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        try {
            System.out.println( "main:start");
            executorService = Executors.newFixedThreadPool( 2);
            Future<?> res1 = executorService.submit( new TaskRun( 1));
            Future<?> res2 = executorService.submit( new TaskRun( 2));
            Future<?> res3 = executorService.submit( new TaskRun( 3));

            System.out.println( "res1.get():" + res1.get()); // Waits if necessary for the computation to complete, and then retrieves its result
            // System.out.println( "res2.get():" + res2.get());
            // System.out.println( "res3.get():" + res3.get());
            System.out.println( "res1.isDone():" + res1.isDone());
            System.out.println( "res2.isDone():" + res2.isDone());
            System.out.println( "res3.isDone():" + res3.isDone());
            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( e);
        } finally {
            if( executorService != null) {
                executorService.shutdown();
                executorService.awaitTermination( 3000, TimeUnit.MILLISECONDS);
                System.out.println( "main:finally complete");
            }
        }

    }
}
