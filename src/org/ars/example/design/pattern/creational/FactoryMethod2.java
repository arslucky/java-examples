package org.ars.example.design.pattern.creational;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * used by {@link java.util.Calendar#getInstance}
 */
public class FactoryMethod2 {

    static Logger log = LogManager.getLogger( FactoryMethod2.class);

    static abstract class A {
        abstract Operation create();

        void action() {
            Operation o = create();
            log.info( o.operation());
        }
    }

    static class FactoryPlus extends A {
        @Override
        Operation create() {
            return new Plus();
        }
    }

    static class FactoryMinus extends A {
        @Override
        Operation create() {
            return new Minus();
        }
    }

    static interface Operation {
        String operation();
    }

    static class Plus implements Operation {
        @Override
        public String operation() {
            return "plus";
        }
    }

    static class Minus implements Operation {
        @Override
        public String operation() {
            return "minus";
        }
    }

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            A a = new FactoryPlus();
            a.action();

            a = new FactoryMinus();
            a.action();

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
