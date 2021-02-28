package me.javalesson;
import java.util.List;

public interface PersonDAO {
    List<Person> getByName(String name);
    List<Person> getByAge(int age);
    void add(Person newPerson);
    Person remove(Person person);
}
