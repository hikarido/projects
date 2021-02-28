package me.javalesson;

public interface PersonService {
    boolean createUser(String name, int age);
    boolean removeUser(String name, int age);
    Person getUser(String name, int age);
}
