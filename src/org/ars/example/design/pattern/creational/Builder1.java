package org.ars.example.design.pattern.creational;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * used by {@link java.lang.StringBuilder#append}
 */
public class Builder1 {

    static Logger log = LogManager.getLogger( Builder1.class);

    static class Car {
        String engine;
        int seats;
        String color;

        public String getEngine() {
            return engine;
        }

        public Car setEngine( String engine) {
            this.engine = engine;
            return this;
        }

        public int getSeats() {
            return seats;
        }

        public Car setSeats( int seats) {
            this.seats = seats;
            return this;
        }

        public String getColor() {
            return color;
        }

        public Car setColor( String color) {
            this.color = color;
            return this;
        }

        @Override
        public String toString() {
            return "Car [engine=" + engine + ", seats=" + seats + ", color=" + color + "]";
        }
    }

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            Car car = new Car().setColor( "red").setEngine( "2.5").setSeats( 5);
            log.info( car);
        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
