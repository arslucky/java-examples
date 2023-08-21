package org.ars.example.stream;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

/**
 * @author arsen.ibragimov
 *
 */
public class Person {

    static Collection<Person> persons = new ArrayList<>();

    static {
        persons.add( new Person( "Jack", "Nickolson", 56));
        persons.add( new Person( "Grag", "Bom", 46));
        persons.add( new Person( "James", "Airy", 12));
        persons.add( new Person( "Edwin", "Bramall", 22));
        persons.add( new Person( "Laura", "Lopes", 14));
        persons.add( new Person( "Nick", "Brown", 88));
        persons.add( new Person( "Edward", "Routh", 22));
    }

    private String name;
    private String family;
    private Integer age;


    public Person( String name, String family, Integer age) {
        this.name = name;
        this.family = family;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily( String family) {
        this.family = family;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge( Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + StringUtils.rightPad( name, 7) + ", family=" + StringUtils.rightPad( family, 10) + ", age=" + age + "]";
    }

}
