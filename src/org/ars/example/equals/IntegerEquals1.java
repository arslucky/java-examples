package org.ars.example.equals;

public class IntegerEquals1 {

    public static void main( String[] args) {
        try {
            Integer a = new Integer( 1);
            Integer b = new Integer( 1);

            System.out.println( a == b);
            System.out.println( a.equals( b));
        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}
