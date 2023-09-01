package org.ars.example.object;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * The new operator instantiates a class by allocating memory for a new object and returning a reference to that memory. The new operator also invokes the object constructor.
 * Note: The phrase "instantiating a class" means the same thing as "creating an object." When you create an object, you are creating an "instance" of a class, therefore "instantiating" a class.
 */
public class LifeCycle1 {
    static Logger log = LogManager.getLogger( LifeCycle1.class);

    static class A {
        private B b = new B();

        static {
            log.info( "instantiation");
        }

        public A() {
            log.info( "initialization");
        }
    }

    static class B {
        private Integer i;

        static {
            log.info( "instantiation");
        }

        public B() {
            log.info( "initialization");
        }
    }

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            A a = new A();
        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
