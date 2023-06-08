package org.ars.example.synchronization;

public class MultupleMonitorObjects1 {

    private Object obj1 = new Object();
    private Object obj2 = new Object();

    private int counter1 = 0;
    private int counter2 = 0;

    public void incCounter1() {
        synchronized( obj1) {
            System.out.println( "obj1:" + this.counter1++);
        }
    }

    public void incCounter2() {
        synchronized( obj2) {
            System.out.println( "obj2:" + this.counter2++);
        }
    }

    public static void main( String[] args) {
        try {
            System.out.println( "main:start");
            MultupleMonitorObjects1 obj = new MultupleMonitorObjects1();

            Thread thread1 = new Thread( () -> {
                for( int i = 0; i < 100; i++) {
                    obj.incCounter1();
                }
            });
            Thread thread2 = new Thread( () -> {
                for( int i = 0; i < 100; i++) {
                    obj.incCounter2();
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
