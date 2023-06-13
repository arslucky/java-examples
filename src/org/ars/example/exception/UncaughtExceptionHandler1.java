package org.ars.example.exception;

public class UncaughtExceptionHandler1 {

    static Thread.UncaughtExceptionHandler h = ( thread, exception) -> {
        System.out.println( "Uncaught exception: " + exception);
    };

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");

            Thread thread = new Thread( () -> {
                try {
                    Thread.sleep( 1000);
                    System.out.println( "task");
                } catch( InterruptedException e) {
                    throw new RuntimeException( "Thread interrupted " + e);
                }
            });

            thread.setUncaughtExceptionHandler( h);
            thread.start();
            thread.interrupt();

            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( "Exception handled " + e);
        }
    }
}
