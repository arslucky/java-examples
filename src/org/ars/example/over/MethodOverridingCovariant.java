package org.ars.example.over;

public class MethodOverridingCovariant {


    public static class A {
        A get() {
            return this;
        }

        void display() {
            System.out.println( "class A");
        }
    }

    public static class B extends A {
        @Override
        B get() {
            return this;
        }

        @Override
        void display() {
            System.out.println( "class B, welcome to covariant return type");
        }
    }

    public static void main( String[] args) {
        try {
            A a = new A();
            a.get().display();

            B b = new B();
            b.get().display();

        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}
