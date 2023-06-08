package org.ars.example.concurrent.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceScheduled1 {

    public static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println( "task");
            return "task result";
        }

    }

    public static void main( String[] args) {
        try {
            System.out.println( "main: start");

            ScheduledExecutorService executorService = Executors.newScheduledThreadPool( 1);

            Future<String> res = executorService.schedule( new Task(), 3000, TimeUnit.MILLISECONDS);

            System.out.println( "res.isDone(): " + res.isDone());

            executorService.shutdown();

            System.out.println( "main: finish");
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
