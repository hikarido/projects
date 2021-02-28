package me.javalesson;

import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService{

    PersonDAO dao;

    public PersonServiceImpl(PersonDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean createUser(String name, int age) {
        dao.add(new Person(name, age));
        return true;
    }

    @Override
    public boolean removeUser(String name, int age) {
        dao.remove(new Person(name, age));
        return true;
    }

    @Override
    public Person getUser(String name, int age) {
        List<Person> persons = dao.getByName(name);
        if(!persons.isEmpty()){
            return persons.get(0);
        }

        persons = dao.getByAge(age);
        if(!persons.isEmpty()){
            return persons.get(0);
        }

        return null;
    }
}
