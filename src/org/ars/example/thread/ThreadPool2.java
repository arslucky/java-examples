package org.ars.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//based on https://jenkov.com/tutorials/java-concurrency/thread-pools.html
//just remained core part to make clear the main idea
public class ThreadPool2 {

    static class ThreadPool {

        private BlockingQueue<Runnable> taskQueue = null;
        private List<PoolThreadRunnable> runnables = new ArrayList<>();

        public ThreadPool(int noOfThreads, int maxNumberOfTasks) {
            taskQueue = new ArrayBlockingQueue<>( maxNumberOfTasks);

            for( int i = 0; i < noOfThreads; i++) {
                PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable( taskQueue);
                runnables.add( poolThreadRunnable);
            }
            runnables.forEach( ( r) -> new Thread( r).start());
        }

        public synchronized void execute( Runnable task) {
            this.taskQueue.offer( task);
        }

        public synchronized void stop() {
            runnables.forEach( ( r) -> r.doStop());
        }

        public synchronized void waitUntilAllTasksFinished() {
            while( this.taskQueue.size() > 0) {
                try {
                    Thread.sleep( 1);
                } catch( InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class PoolThreadRunnable implements Runnable {

        private BlockingQueue<Runnable> taskQueue;
        private boolean isStopped;
        private Thread thread = null;

        public PoolThreadRunnable( BlockingQueue<Runnable> taskQueue) {
            this.taskQueue = taskQueue;
        }

        @Override
        public void run() {
            this.thread = Thread.currentThread();
            while( !isStopped) {
                try {
                    Runnable task = taskQueue.take();
                    task.run(); // run in this thread
                } catch( InterruptedException e) {
                    // e.printStackTrace();
                }
            }
        }

        public synchronized void doStop() {
            isStopped = true;
            this.thread.interrupt();
        }
    }

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");

            ThreadPool threadPool = new ThreadPool( 3, 10);

            for( int i = 0; i < 10; i++) {
                int taskNo = i;
                threadPool.execute( () -> {
                    System.out.println( Thread.currentThread().getName() + ": task:" + taskNo);
                });
            }

            threadPool.waitUntilAllTasksFinished();
            threadPool.stop();

            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
