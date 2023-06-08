package org.ars.example.exception;

public class ExceptionThrow {

    public static class A {
        void a() {
            // throw new IOException(); // Checked, Unhandled exception type IOException,
        }

        void b() {
            throw new NullPointerException(); // Unchecked
        }
    }
}
