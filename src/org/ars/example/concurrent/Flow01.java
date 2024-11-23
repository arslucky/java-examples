package org.ars.example.concurrent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Flow;
/*
Describes Flow/reactive-streams specification
 */
@Log4j2
public class Flow01 {
    @Getter
    @AllArgsConstructor
    static class Publisher01 implements Flow.Publisher<String> {
        private String[] data;
        @Override
        public void subscribe(Flow.Subscriber<? super String> subscriber) {
            subscriber.onSubscribe(new Flow.Subscription() {
                private int index = 0;
                private boolean canceled = false;
                @Override
                public void request(long n) {
                    for (int i = 0; i < n && index < data.length && !canceled; i++) {
                        subscriber.onNext(data[index++]);
                    }
                    if (index == data.length) {
                        subscriber.onComplete();
                    }
                }
                @Override
                public void cancel() {
                    canceled = true;
                }
            });
        }
    }
    static class Subscriber01 implements Flow.Subscriber<String> {
        Flow.Subscription subscription;
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }
        @Override
        public void onNext(String item) {
            log.info(item);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }

        @Override
        public void onComplete() {
            log.info("all items received");
        }
    }

    public static void main(String[] args) {
        try {
            String[] data = {"Hello", "my", "friend!"};
            var publisher = new Publisher01(data);
            publisher.subscribe(new Subscriber01());
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
