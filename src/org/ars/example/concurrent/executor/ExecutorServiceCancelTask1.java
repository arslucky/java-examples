package org.ars.example.concurrent.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceCancelTask1 {

    static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println( "task:begin");
            Thread.sleep( 1000);
            System.out.println( "task finished");
            return "task:end";
        }
    }

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");
            ExecutorService executorService = Executors.newSingleThreadExecutor();

            Future<String> res = executorService.submit( new Task());
            System.out.println( "res.isDone(): " + res.isDone());

            boolean canceled = res.cancel( false); // when true than try to interrupt running task
            System.out.println( "canceled: " + canceled); // hm, returns true when task is still running
            System.out.println( "res.isDone(): " + res.isDone()); // After calling 'cancel' method, subsequent calls to isDone will always return true.
                                                                  // Note: task is still running

            executorService.shutdown();

            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
