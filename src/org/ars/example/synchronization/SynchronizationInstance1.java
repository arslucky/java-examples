package org.ars.example.synchronization;

public class SynchronizationInstance1 {

    static class SynchronizedExchanger {

        private Object obj = null;

        synchronized void setObject( Object obj) {
            this.obj = obj;
        }

        void setObj( Object obj) {
            synchronized( this) { // same as setObject()
                this.obj = obj;
            }
        }

        synchronized Object getObject() {
            return this.obj;
        }

        Object getObj() {
            synchronized( this) { // same as getObject()
                return this.obj;
            }
        }
    }

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");
            SynchronizedExchanger obj = new SynchronizedExchanger();


            Thread thread1 = new Thread( () -> {
                for( int i = 0; i < 1000; i++) {
                    obj.setObject( "" + i);
                }
            });

            Thread thread2 = new Thread( () -> {
                for( int i = 0; i < 1000; i++) {
                    System.out.println( obj.getObject());
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
