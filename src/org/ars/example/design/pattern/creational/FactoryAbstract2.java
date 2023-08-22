package org.ars.example.design.pattern.creational;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * used by {@link javax.xml.parsers.DocumentBuilderFactory#newInstance}
 */
public class FactoryAbstract2 {

    static Logger log = LogManager.getLogger( FactoryAbstract2.class);

    static interface Factory {
        Product createProduct();
        Product createPairProduct();
    }

    static class Factory1 implements Factory {
        @Override
        public Product createProduct() {
            return new Product1();
        }

        @Override
        public Product createPairProduct() {
            return new Product3();
        }
    }

    static class Factory2 implements Factory {
        @Override
        public Product createProduct() {
            return new Product2();
        }

        @Override
        public Product createPairProduct() {
            return new Product3();
        }
    }

    // ----------------------//
    static interface Product {
        String produce();
    }

    static class Product1 implements Product {
        @Override
        public String produce() {
            return "product1";
        }
    }

    static class Product2 implements Product {
        @Override
        public String produce() {
            return "product2";
        }
    }

    static class Product3 implements Product {
        @Override
        public String produce() {
            return "product3";
        }
    }

    // ----------------------//
    static class Application {
        Factory factory;

        Application( Factory factory) {
            this.factory = factory;
        }

        void produce() {
            log.info( "produce {}, {}", factory.createProduct().produce(), factory.createPairProduct().produce());
        }
    }

    // ----------------------//
    public static void main( String[] args) {
        try {
            log.info( "main:start");

            Factory factory = new Factory1();
            Application app = new Application( factory);
            app.produce();

            factory = new Factory2();
            app = new Application( factory);
            app.produce();

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}