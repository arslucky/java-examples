package org.ars.example.over;

//compile time polymorphism
public class MethodOverloading {

    static void display( int a) {
        System.out.println( String.format( "a: %s", a));
    }

    static void display( int a, int b) {
        System.out.println( String.format( "a: %s, b: %s", a, b));
    }

    void display( int a, int b, int c) {
        System.out.println( String.format( "a: %s, b: %s, c: %s", a, b, c));
    }

    static public void main( String[] args) {
        display( 1);
        display( 2, 3);
        new MethodOverloading().display( 4, 5, 6);
    }
}
