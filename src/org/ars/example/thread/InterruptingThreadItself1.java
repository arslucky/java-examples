package org.ars.example.thread;

public class InterruptingThreadItself1 {

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");

            Thread thread = new Thread( () -> {
                System.out.println( Thread.currentThread().getName() + ":running");
                Thread.currentThread().interrupt();
                System.out.println( Thread.currentThread().getName() + ":interrupted");
            });

            thread.start();
            thread.interrupt();
            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
