package me.javalesson;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PersonServiceImplTest {
    @Test
    void shouldCreate() {
        PersonDAO dao = Mockito.mock(PersonDAO.class);
        PersonService service = new PersonServiceImpl(dao);
    }

    @Test
    void shouldReturnPersonWithEqualAge() {
        PersonDAO dao = Mockito.mock(PersonDAO.class);
        List<Person> personsWithAgeEquals10 = new LinkedList<>();
        personsWithAgeEquals10.add(new Person("a", 10));
        Mockito.when(dao.getByAge(10)).thenReturn(personsWithAgeEquals10);
        PersonService service = new PersonServiceImpl(dao);
        Person user = service.getUser("x", 10);
        Assertions.assertEquals(user.getAge(), personsWithAgeEquals10.get(0).getAge());
    }

    @Test
    void shouldReturnPersonWithEqualName() {
        PersonDAO dao = Mockito.mock(PersonDAO.class);
        List<Person> personsWithAgeEquals10 = new LinkedList<>();
        personsWithAgeEquals10.add(new Person("a", 10));
        Mockito.when(dao.getByName("a")).thenReturn(personsWithAgeEquals10);
        PersonService service = new PersonServiceImpl(dao);
        Person user = service.getUser("a", 22);
        Assertions.assertEquals(user.getName(), personsWithAgeEquals10.get(0).getName());
    }

    @Test
    void shouldReturnRecentlyCreateUser() {
        PersonDAO dao = Mockito.mock(PersonDAO.class);
        String name = "a";
        int age = 45;
        List<Person> persons = new LinkedList<>();
        persons.add(new Person(name, age));
        Mockito.when(dao.getByName(name)).thenReturn(persons);
        PersonService service = new PersonServiceImpl(dao);
        service.createUser(name, age);
        Person expected = new Person(name, age);
        Person retrieved = service.getUser(name, age);
        Assertions.assertEquals(expected, retrieved);
    }

    @Test
    void addUserToEmptyBackendAndThenRemoveItFromThenTryGetUserFromEmptyBack(){
        PersonDAO dao = Mockito.mock(PersonDAO.class);
        Mockito.when(dao.getByName(Mockito.anyString())).thenReturn(Collections.emptyList());
        Mockito.when(dao.getByAge(Mockito.anyInt())).thenReturn(Collections.emptyList());
        PersonServiceImpl service = new PersonServiceImpl(dao);

        String name = "a";
        int age = 1;
        service.createUser(name, age);
        service.removeUser(name, age);
        Person person = service.getUser(name, age);
        Assertions.assertNull(person);
    }
}
