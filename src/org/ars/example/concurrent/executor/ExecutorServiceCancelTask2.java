package org.ars.example.concurrent.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceCancelTask2 {

    static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                System.out.println( "task:begin");
                Thread.sleep( 1000);
                System.out.println( "task finished");
            } catch( InterruptedException e) {
                System.out.println( "task: " + e);
            }
            return "task:end";
        }
    }

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");
            ExecutorService executorService = Executors.newSingleThreadExecutor();

            Future<String> res = executorService.submit( new Task());
            Thread.sleep( 200);

            boolean cancel = res.cancel( true);
            System.out.println( "cancel: " + cancel);

            executorService.shutdown();

            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
