package org.ars.example.concurrent.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceObjectWait1 {

    static class Task implements Callable<String> {

        Object obj;

        public Task( Object obj) {
            this.obj = obj;
        }

        @Override
        public String call() throws Exception {
            try {
                System.out.println( "task:begin");
                Thread.sleep( 1000);
                this.obj.notify();
                System.out.println( "task finished");
            } catch( Exception e) {
                System.out.println( "task: " + e);
            }
            return "task:end";
        }
    }

    public static void main( String[] args) {

        ExecutorService executorService = null;
        try {
            System.out.println( "main:start");
            executorService = Executors.newSingleThreadExecutor();

            Object obj = new Object();

            Task task = new Task( obj);
            Future<String> res = executorService.submit( task);
            System.out.println( "obj:wait:start");
            obj.wait();
            System.out.println( "obj:wait:stop");
            // Thread.sleep( 1000);

            // task.notify();
            // boolean cancel = res.cancel( true);
            // System.out.println( "cancel: " + cancel);

            executorService.shutdown();

            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( e);
        } finally {
            executorService.shutdown();
        }
    }
}
