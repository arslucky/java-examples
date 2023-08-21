package org.ars.example.design.pattern.creational;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * used by {@link javax.xml.parsers.DocumentBuilderFactory#newInstance}
 */
public class FactoryAbstract1 {

    static Logger log = LogManager.getLogger( FactoryAbstract1.class);

    static interface Transport {
        String moveBy();
    }

    static class Car implements Transport {
        @Override
        public String moveBy() {
            return "road";
        }
    }

    static class Board implements Transport {
        @Override
        public String moveBy() {
            return "water";
        }
    }
    // ------------------- Factory ------------------------//
    static interface Delivery { // Factory interface
        Transport deliveryBy();
    }

    static class DeliveryCar implements Delivery {
        @Override
        public Transport deliveryBy() {
            return new Car();
        }
    }

    static class DeliveryBoard implements Delivery {
        @Override
        public Transport deliveryBy() {
            return new Board();
        }
    }

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            Delivery car = new DeliveryCar();
            Delivery board = new DeliveryBoard();

            log.info( "Delivery by: {}", car.deliveryBy().moveBy());
            log.info( "Delivery by: {}", board.deliveryBy().moveBy());

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
