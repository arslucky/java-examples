package org.ars.example.synchronization;

public class SynchronizationStatic1 {

    static class SynchronizedExchanger {

        static private Object staticObj = null;

        static synchronized void setObject( Object obj) {
            staticObj = obj;
        }

        static void setObj( Object obj) {
            synchronized( SynchronizedExchanger.class) { // same as setObject()
                staticObj = obj;
            }
        }

        static synchronized Object getObject() {
            return staticObj;
        }

        static Object getObj() {
            synchronized( SynchronizedExchanger.class) { // same as getObject()
                return staticObj;
            }
        }
    }

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");

            Thread thread1 = new Thread( () -> {
                for( int i = 0; i < 1000; i++) {
                    SynchronizedExchanger.setObject( "" + i);
                }
            });

            Thread thread2 = new Thread( () -> {
                for( int i = 0; i < 1000; i++) {
                    System.out.println( SynchronizedExchanger.getObject());
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
