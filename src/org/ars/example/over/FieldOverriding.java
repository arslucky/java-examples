package org.ars.example.over;

public class FieldOverriding {

    public static class A {
        static String value = "A";
        String val = "AA";
    }

    // static nested class
    static class B1 extends A {
        static String value = "B1";
    }

    // static nested class
    static class B2 extends A {
        String value = "B2";
        String val = "BB2";
    }

    // inner nested class
    class B3 extends A {
        // The field value cannot be declared static in a non-static inner type, unless initialized with a constant expression
        // static String value = "B3";

        String value = "B3";
    }

    static public void main( String[] args) {
        System.out.println( A.value);
        System.out.println( B1.value);

        A a = new B1();
        System.out.println( a.value); // The static field FeldOverriding.A.value should be accessed in a static way
        System.out.println( a.val);

        B1 b1 = new B1();
        System.out.println( b1.value); // The static field FeldOverriding.B1.value should be accessed in a static way

        a = new B2();
        System.out.println( a.value); // The static field FeldOverriding.A.value should be accessed in a static way

        B2 b2 = new B2();
        System.out.println( b2.value);
        System.out.println( b2.val);

        // Child2 child2 = new Child2();
        // System.out.println( "child2, value: " + child2.value);
    }



}
