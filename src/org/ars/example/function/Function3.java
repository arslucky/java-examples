package org.ars.example.function;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.function.Function;
@Log4j2
class Function3 {
    public static void main(String[] args) {
        try {
            Function<Integer, Integer> f = (a) -> {log.info("item: {}", a); return a;};
            var list = List.of(1,2);
            list.stream().map(f.andThen(a -> {a*=10; log.info("after: {}", a); return a;})).forEach(log::info);
            list.stream().map(f.compose(a -> {a*=10; log.info("before: {}", a); return a;})).forEach(log::info);
            list.stream().map(Function.identity()).forEach(log::info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
