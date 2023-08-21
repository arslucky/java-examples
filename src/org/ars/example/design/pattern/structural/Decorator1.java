package org.ars.example.design.pattern.structural;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author arsen.ibragimov
 *
 * used by {@link java.io.InputStream} subclasses, {@link javax.servlet.http.HttpServletRequestWrapper}
 *
 */
public class Decorator1 { // Wrapper

    static Logger log = LogManager.getLogger( Decorator1.class);

    static interface Notifier {
        void send( String message);
    }

    static final class EmailNotifier implements Notifier { // base class

        @Override
        public void send( String message) {
            log.info( "send email: {}", message);
        }
    }

    static class NotifyDecorator implements Notifier {

        private Notifier wrapper;

        public NotifyDecorator( Notifier notifier) {
            this.wrapper = notifier;
        }

        @Override
        public void send( String message) {
            this.wrapper.send( message);
        }
    }

    static class SmsNotifier extends NotifyDecorator {

        public SmsNotifier( Notifier notifier) {
            super( notifier);
        }

        @Override
        public void send( String message) {
            super.send( message);
            log.info( "send sms: {}", message);
        }
    }

    static class SlackNotifier extends NotifyDecorator {

        public SlackNotifier( Notifier notifier) {
            super( notifier);
        }

        @Override
        public void send( String message) {
            super.send( message);
            log.info( "send slack: {}", message);
        }
    }

    public static void main( String[] args) {
        try {
            log.info( "main:start");

            NotifyDecorator notifyDecorator = new SlackNotifier( new SmsNotifier( new EmailNotifier()));
            notifyDecorator.send( "Hello!");

        } catch( Exception e) {
            log.error( e.getMessage(), e);
        } finally {
            log.info( "main:finish");
        }
    }
}
