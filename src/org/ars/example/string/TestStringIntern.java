package org.ars.example.string;

public class TestStringIntern {
    public static void main( String[] args) {
        try {
            String a = "a";
            String aa = new String( "a");

            System.out.println( a.equals( aa));
            System.out.println( a == aa);
            System.out.println( a == aa.intern());
        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}
