package org.ars.example.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author arsen.ibragimov
 *
 */
public class RegExp2 {
    public static void main( String[] args) {
        Pattern pattern = Pattern.compile( "\\$\\{([\\w\\.]+)\\}");
        try {
            String s = "${KAFKA_HOST1}:${KAFKA_PORT1},${KAFKA_HOST2}:${KAFKA_PORT2},${KAFKA_HOST3}:${KAFKA_PORT3}";
            int i = 0;
            while( true) {
                Matcher matcher = pattern.matcher( s);
                boolean find = matcher.find();
                System.out.println( "matcher.find():" + find);
                if( !find || i > 10) {
                    System.out.println( "break");
                    break;
                }
                s = s.replaceFirst( pattern.pattern(), "" + (i++));
                System.out.println( "s=" + s);
            }
        } catch( Exception e) {
            e.printStackTrace();
        }
    }
}
