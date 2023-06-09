package org.ars.example.concurrent;

//Volatile doesn't garantee exclusive access
public class ConcurrentVolatile1 {

    private volatile int count = 0;

    void increment() {
        this.count++;
        // read variable from main memory
        // increment variable in the tread memory
        // write variable to main memory
    }

    int get() {
        return this.count;
    }

    public static void main( String[] args) {
        try {
            ConcurrentVolatile1 concurrentVolatile1 = new ConcurrentVolatile1();

            Thread thread1 = new Thread( () -> {
                for( int i = 0; i < 1_000_000; i++) {
                    concurrentVolatile1.increment();
                }
                System.out.println( concurrentVolatile1.get());
            });

            Thread thread2 = new Thread( () -> {
                for( int i = 0; i < 1_000_000; i++) {
                    concurrentVolatile1.increment();
                }
                System.out.println( concurrentVolatile1.get());
            });

            thread1.start();
            thread2.start();
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
