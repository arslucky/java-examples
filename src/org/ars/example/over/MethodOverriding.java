package org.ars.example.over;

//runtime polymorphism
public class MethodOverriding {

    static void display( int a) {
        System.out.println( String.format( "parent, a: %s", a));
    }

    void display( int a, int b) {
        System.out.println( String.format( "parent, a: %s, b: %s", a, b));
    }

    static public void main( String[] args) {
        display( 1);
        MethodOverriding parent = new MethodOverriding();
        parent.display( 2, 3);
        Child1.display( 4);
        MethodOverriding child1 = new Child1();
        child1.display( 5, 6);

        System.out.println( "-----------------------------");

        parent = new Child1();
        parent.display( 1); // the static method display(int) from the type MethodOverriding should be accessed in a static way
        parent.display( 7, 8);
    }

    // static nested class
    static class Child1 extends MethodOverriding {
        static void display( int a) {
            System.out.println( String.format( "child 1, a: %s", a));
        }

        // This instance method cannot override the static method from MethodOverriding
        /*
         * void display( int a) {
         * System.out.println( String.format( "child 1, a: %s", a));
         * }
         */

        @Override
        void display( int a, int b) {
            System.out.println( String.format( "child 1, a: %s, b: %s", a, b));
        }
    }

    // inner nested class
    class Child2 extends MethodOverriding {
        // The method display cannot be declared static; static methods can only be declared in a static or top level type
        /*
         * static void display( int a) {
         * System.out.println( "Value of a : " + a);
         * }
         */

        // This instance method cannot override the static method from MethodOverriding
        /*
         * void display( int a) {
         * System.out.println( "Value of a : " + a);
         * }
         */
    }

}
