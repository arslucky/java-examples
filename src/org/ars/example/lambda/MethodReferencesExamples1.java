package org.ars.example.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;

public class MethodReferencesExamples1 {
    
    public static <T> T mergeThings(T a, T b, BiFunction<T, T, T> merger) {
        return merger.apply(a, b);
    }
    
    public static String appendStrings(String a, String b) {
        return a + b;
    }
    
    public String appendStrings2(String a, String b) {
        return a + b;
    }

    public static void main(String[] args) {
        
        MethodReferencesExamples1 myApp = new MethodReferencesExamples1();

        // Calling the method mergeThings with a lambda expression
        System.out.println(MethodReferencesExamples1.
            mergeThings("Hello ", "World!", (a, b) -> a + b));
        
        // Reference to a static method
        System.out.println(MethodReferencesExamples1.
            mergeThings("Hello ", "World!", MethodReferencesExamples1::appendStrings));

        // Reference to an instance method of a particular object        
        System.out.println(MethodReferencesExamples1.
            mergeThings("Hello ", "World!", myApp::appendStrings2));
        
        // Reference to an instance method of an arbitrary object of a
        // particular type
        System.out.println(MethodReferencesExamples1.
            mergeThings("Hello ", "World!", String::concat));
        
        String[] stringArray = { "Barbara", "James", "Mary", "John",
        	    "Patricia", "Robert", "Michael", "Linda" };
        	Arrays.sort(stringArray, String::compareToIgnoreCase);
        
        //	Arrays.sort(
        //Comparator c = Comparator.comparing
    }
}
