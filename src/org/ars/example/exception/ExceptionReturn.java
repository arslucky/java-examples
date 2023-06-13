package org.ars.example.exception;

public class ExceptionReturn {

    void finnally() {
        try {
            System.out.println( "try");
            int i = 1;
            System.out.println( i / 0);

        } catch( Exception e) {
            System.out.println( "catch");
            return;
        } finally {
            System.out.println( "finally");
        }
    }

    String get() {
        try {
            System.out.println( "try");
            int i = 1;
            System.out.println( i / 0);
            return "try";
        } catch( Exception e) {
            System.out.println( "catch");
            return "catch";
        } finally {
            System.out.println( "finally");
            return "finally";
        }
    }

    public static void main( String[] args) {
        try {
            ExceptionReturn exc = new ExceptionReturn();
            System.out.println( "get:" + exc.get());
            exc.finnally();
        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}
