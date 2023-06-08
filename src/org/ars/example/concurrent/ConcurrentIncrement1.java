package org.ars.example.concurrent;

/*https://www.youtube.com/watch?v=LCSqZyjBwWA&list=PLL8woMHwr36EDxjUoCzboZjedsnhLP1j4&index=4

CPU
 |
 CPU Registers
 |
 L1,L2,L3 cache
 |
 RAM
 |
 HEAP
*/
public class ConcurrentIncrement1 {

    static class RunnableTask implements Runnable {

        int count = 0;

        @Override
        public void run() {
            for( int i = 0; i < 1_000_000; i++) {
                count++;
            }
            System.out.println( Thread.currentThread().getName() + ", count: " + count);
        }

    }

    public static void main( String[] args) {
        try {
            RunnableTask task1 = new RunnableTask();
            RunnableTask task2 = new RunnableTask();

            Thread thread1 = new Thread( task1);
            Thread thread2 = new Thread( task2);

            // result successful, expected
            thread1.start();
            thread2.start();
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
