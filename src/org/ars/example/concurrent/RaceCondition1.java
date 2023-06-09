package org.ars.example.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

//https://www.youtube.com/watch?v=RMR75VzYoos&list=PLL8woMHwr36EDxjUoCzboZjedsnhLP1j4&index=10
public class RaceCondition1 {

    static Runnable getRunnable( ConcurrentMap<String, String> hashMap) {
        return () -> {
            for( int i = 0; i < 10_000; i++) {
                if( hashMap.containsKey( "key")) {
                    String value = hashMap.remove( "key");
                    if( value == null) {
                        System.out.println( "Iteartion: " + i + ": Value for 'key' is null");
                    }
                } else {
                    hashMap.put( "key", "value");
                }
            }
        };
    }

    public static void main( String[] args) {
        try {
            ConcurrentMap<String, String> hashMap = new ConcurrentHashMap<>();
            Thread thread1 = new Thread( getRunnable( hashMap));
            Thread thread2 = new Thread( getRunnable( hashMap));

            thread1.start();
            thread2.start();

        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
