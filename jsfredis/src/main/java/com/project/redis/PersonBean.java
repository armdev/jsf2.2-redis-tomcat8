package com.project.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import redis.clients.jedis.Jedis;

@ManagedBean(name = "personBean")
@SessionScoped
public class PersonBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Person person = new Person();

    private Jedis jedis;

    private Set<String> set = new HashSet<>();

    public PersonBean() {
    }

    @PostConstruct
    private void initDB() {
//         Start embedded Redis
        jedis = new Jedis("localhost", 6379);
    }

    @PreDestroy
    private void stopDB() {
        jedis.shutdown();
    }

    public void createPerson() {
        jedis.set(person.getName(), new Person(person.getName(), person.getAge()).toString());
        Person retrunBack = Person.fromString(jedis.get(person.getName()));
        //System.out.println("retrunBack " + retrunBack.getName());
        set.add(person.getName());
    }

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        set.forEach((key) -> {
            persons.add(Person.fromString(jedis.get(key)));
        }); //System.out.println("persons " + persons.size());

        return persons;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

}
