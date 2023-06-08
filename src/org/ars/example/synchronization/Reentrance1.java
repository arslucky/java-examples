package org.ars.example.synchronization;

public class Reentrance1 {

    private int count = 0;

    synchronized void inc() {
        this.count++;
    }

    synchronized int incAndGet() {
        inc();
        return this.count;
    }

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");
            Reentrance1 reentrance1 = new Reentrance1();

            Thread thread1 = new Thread( () -> {
                    for( int i = 0; i < 1000; i++) {
                        System.out.println( Thread.currentThread().getName() + ":" + reentrance1.incAndGet());
                    }
            });

            Thread thread2 = new Thread( () -> {
                for( int i = 0; i < 1000; i++) {
                    System.out.println( Thread.currentThread().getName() + ":" + reentrance1.incAndGet());
                }
            });

            thread1.start();
            thread2.start();

            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
