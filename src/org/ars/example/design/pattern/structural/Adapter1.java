package org.ars.example.design.pattern.structural;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 */
public class Adapter1 {

    static Logger log = LogManager.getLogger( Adapter1.class);

    static class Service {
        void order( String app, String orderNum) {
            log.info( "app: {}, orderNum: {}", app, orderNum);
        }
    }

    static interface Client {
        void order( String orderNum);
    }

    static class MarketClient implements Client {
        @Override
        public void order( String orderNum) {
            log.info( "orderNum: {}", orderNum);
        }
    }

    static class MarketClientAdapter extends MarketClient {

        Service service;

        public MarketClientAdapter( Service service) {
            this.service = service;
        }

        @Override
        public void order( String orderNum) {
            this.service.order( "adapterApp", orderNum);
        }
    }

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            Client marketClient = new MarketClient();
            marketClient.order( "123");

            marketClient = new MarketClientAdapter( new Service());
            marketClient.order( "123");

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
