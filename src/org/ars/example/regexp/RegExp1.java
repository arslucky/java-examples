package org.ars.example.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp1 {
    public static void main( String[] args) {
        Pattern pattern = Pattern.compile( "\\$\\{([\\w\\.]+)\\}");
        try {
            String s = "${aaa.bbb}";
            Matcher matcher = pattern.matcher( s);
            System.out.println( matcher.find());
            System.out.println( matcher.matches());
            System.out.println( matcher.groupCount());
            System.out.println( matcher.group( 1));
            // System.out.println( "s=" + s.replaceAll( "\\$\\{.+\\}", ""));
        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}
