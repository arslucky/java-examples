package org.ars.example.synchronization;

public class SharedMonitorObject1 {

    private Object monitor = null;

    private int counter = 0;

    public SharedMonitorObject1( Object monitor) {
        if( monitor == null) {
            throw new IllegalArgumentException( "Monitor object cannot be null");
        }
        this.monitor = monitor;
    }

    public void incCounter() {
        synchronized( monitor) {
            System.out.println( Thread.currentThread().getName() + ":" + this.counter++);
        }
    }

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");
            Object monitor = new Object();

            SharedMonitorObject1 obj1 = new SharedMonitorObject1( monitor);
            SharedMonitorObject1 obj2 = new SharedMonitorObject1( monitor);

            Thread thread1 = new Thread( () -> {
                for( int i = 0; i < 1000; i++) {
                    obj1.incCounter();
                }
            });
            Thread thread2 = new Thread( () -> {
                for( int i = 0; i < 1000; i++) {
                    obj2.incCounter();
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
