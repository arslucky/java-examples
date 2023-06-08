package org.ars.example.lang;

public class InstanceOf1 {

    public static class A {
        ;
    }

    // static nested class
    static class B extends A {
        ;
    }

    static public void main( String[] args) {
        A a = new B();

        System.out.println( a instanceof A);
        System.out.println( a instanceof B);
    }



}
