package org.ars.example.concurrent;

public class ThreadLocal1 {

    public static void main( String[] args) {
        try {
            ThreadLocal<String> threadLocal = new ThreadLocal<>();
            InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

            Thread thread1 = new Thread( () -> {
                System.out.println( "==== Thread 1 ===");
                threadLocal.set( "Thread 1 - ThreadLocal");
                inheritableThreadLocal.set( "Thread 1 - inheritableThreadLocal");

                System.out.println( threadLocal.get());
                System.out.println( inheritableThreadLocal.get());

                Thread childThread = new Thread( () -> {
                    System.out.println( "==== ChildThread ===");
                    System.out.println( threadLocal.get());
                    System.out.println( inheritableThreadLocal.get());
                });

                childThread.start();
            });

            thread1.start();
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
