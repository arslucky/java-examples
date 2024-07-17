package org.ars.example.stream.collect;

import lombok.extern.log4j.Log4j2;

import java.util.HashSet;
import java.util.stream.IntStream;

@Log4j2
public class Collect01 {
    public static void main(String[] args) {
        try {
            var collect = IntStream.of(1, 2, 3, 3).collect(HashSet::new, HashSet::add, HashSet::addAll);
            log.info(collect);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
