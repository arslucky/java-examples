package org.ars.example.concurrent;

/* https://www.youtube.com/watch?v=tLS85IfsbYE&list=PLL8woMHwr36EDxjUoCzboZjedsnhLP1j4&index=22
 *
 * False sharing in Java occurs when two threads running on two different CPUs write to two different variables which happen to be stored within the same CPU cache line.
 * When the first thread modifies one of the variables-the whole CPU cache line is invalidated in the CPU caches of the other CPU where the other thread is running.
 * This means,that the other CPUs need to reload the content of the invalidated cache line-even if they don'treally need the variable that was modified within that cache line.
*/
public class FalseSharing1 {

    public static class Counter {
        // @jdk.internal.vm.annotation.Contended
        // @sun.misc.Contended
        volatile long count1 = 0;
        volatile long count2 = 0;
    }

    public static void main( String[] args) {
        try {
            Counter counter = new Counter();

            Thread thread1 = new Thread( () -> {
                long start = System.currentTimeMillis();
                for( int i = 0; i < 1_000_000_000; i++) {
                    counter.count1++;
                }
                System.out.println( System.currentTimeMillis() - start);
            });

            Thread thread2 = new Thread( () -> {
                long start = System.currentTimeMillis();
                for( int i = 0; i < 1_000_000_000; i++) {
                    counter.count2++;
                }
                System.out.println( System.currentTimeMillis() - start);
            });

            thread1.start();
            thread2.start();

            // False sharing
            // 33048
            // 33048
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
