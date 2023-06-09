package org.ars.example.concurrent.synchronization;

public class SynchronizationMixed1 {

    static class SynchronizedExchanger {

        static private Object staticObj = null; // monitor 1

        private Object instanceObj = null; // monitor 2

        static synchronized void setStaticObject( Object obj) {
            staticObj = obj;
        }

        synchronized void setInstanceObject( Object obj) {
            instanceObj = obj;
        }

        static synchronized Object getStaticObject() {
            return staticObj;
        }

        synchronized Object getInstanceObject() {
            return instanceObj;
        }
    }

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");

            Thread threadStatic1 = new Thread( () -> {
                for( int i = 0; i < 1000; i++) {
                    SynchronizedExchanger.setStaticObject( "" + i);
                }
            });

            Thread threadStatic2 = new Thread( () -> {
                for( int i = 0; i < 1000; i++) {
                    System.out.println( "static:" + SynchronizedExchanger.getStaticObject());
                }
            });

            SynchronizedExchanger obj = new SynchronizedExchanger();

            Thread threadInstance1 = new Thread( () -> {
                for( int i = 0; i < 1000; i++) {
                    obj.setInstanceObject( "" + i);
                }
            });

            Thread threadInstance2 = new Thread( () -> {
                for( int i = 0; i < 1000; i++) {
                    System.out.println( "instance:" + obj.getInstanceObject());
                }
            });

            threadStatic1.start();
            threadStatic2.start();

            threadInstance1.start();
            threadInstance2.start();

            System.out.println( "main:finish");
        } catch( Exception e) {
            System.out.println( e);
        }
    }
}
