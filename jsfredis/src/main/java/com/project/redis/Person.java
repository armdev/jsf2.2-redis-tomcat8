package com.project.redis;

import java.io.Serializable;
import java.util.StringTokenizer;

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Person fromString(String string) {
        StringTokenizer tokens = new StringTokenizer(string, ",");
        return new Person(tokens.nextToken(), Integer.parseInt(tokens.nextToken().trim()));
    }

    private String name;

    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + ", " + age;
    }

}
