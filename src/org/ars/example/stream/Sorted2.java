package org.ars.example.stream;

import lombok.extern.log4j.Log4j2;

import java.util.Comparator;
/*
The last sorted operation overrides the previous
 */
@Log4j2
public class Sorted2 {
    public static void main(String[] args) {
        try {
            Person.persons.stream()
                    .sorted(Comparator.comparing(Person::getName))
                    .sorted(Comparator.comparing(Person::getAge))
                    .forEach(log::info);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
