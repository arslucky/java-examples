package org.ars.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//https://jenkov.com/tutorials/java-concurrency/thread-pools.html
public class ThreadPool1 {

    public static class ThreadPool {

        private BlockingQueue taskQueue = null;
        private List<PoolThreadRunnable> runnables = new ArrayList<>();
        private boolean isStopped = false;

        public ThreadPool( int noOfThreads, int maxNoOfTasks) {
            taskQueue = new ArrayBlockingQueue( maxNoOfTasks);

            for( int i = 0; i < noOfThreads; i++) {
                PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable( taskQueue);

                runnables.add( poolThreadRunnable);
            }
            for( PoolThreadRunnable runnable : runnables) {
                new Thread( runnable).start();
            }
        }

        public synchronized void execute( Runnable task) throws Exception {
            if( this.isStopped)
                throw new IllegalStateException( "ThreadPool is stopped");

            this.taskQueue.offer( task);
        }

        public synchronized void stop() {
            this.isStopped = true;
            for( PoolThreadRunnable runnable : runnables) {
                runnable.doStop();
            }
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

    public static class PoolThreadRunnable implements Runnable {

        private Thread thread = null;
        private BlockingQueue taskQueue = null;
        private boolean isStopped = false;

        public PoolThreadRunnable( BlockingQueue queue) {
            taskQueue = queue;
        }

        @Override
        public void run() {
            this.thread = Thread.currentThread();
            while( !isStopped()) {
                try {
                    Runnable runnable = (Runnable) taskQueue.take();
                    runnable.run();
                } catch( Exception e) {
                    // log or otherwise report exception,
                    // but keep pool thread alive.
                }
            }
        }

        public synchronized void doStop() {
            isStopped = true;
            // break pool thread out of dequeue() call.
            this.thread.interrupt();
        }

        public synchronized boolean isStopped() {
            return isStopped;
        }
    }

    public static void main( String[] args) throws Exception {

        ThreadPool threadPool = new ThreadPool( 3, 10);

        for( int i = 0; i < 10; i++) {

            int taskNo = i;
            threadPool.execute( () -> {
                String message = Thread.currentThread().getName() + ": Task " + taskNo;
                System.out.println( message);
            });
        }

        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();

    }
}
