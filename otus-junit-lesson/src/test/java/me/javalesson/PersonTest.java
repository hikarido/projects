package me.javalesson;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    void itShouldCreatesCorrectlyByDefaultConstructorTest() {
        Person p = new Person("hero", 10);
        assertTrue(p != null);
    }

    @Test
    void nameGivenInConstructorMustBeReturnedByGetter() {
        final String expectedName = "Hero";
        Person p = new Person(expectedName, 10);
        Assertions.assertThat(p.getName()).isEqualTo(expectedName);
    }

    @Test
    void ageGivenInConstructorMustBeReturnedByGetter() {
        int expectedAge = 10;
        Person person = new Person("", expectedAge);
        Assertions.assertThat(person.getAge()).isEqualTo(expectedAge);
    }

    @Test
    void mustThrowException() {
        Person p = new Person("", 1);
        Assertions.assertThatThrownBy(p::alwaysThrowsException);
    }

    @Test
    void mustThrowRuntimeException() {
        Person person = new Person("", 1);
        Assertions
                .assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(person::alwaysThrowsException);
    }

    @Test
    void equalsTest(){
        Person personA = new Person("", 1);
        Person personB = new Person("", 1);
        assertEquals(personA, personB);
    }

    @Test
    void notEqualsNameTest(){
        Person personA = new Person("a", 1);
        Person personB = new Person("b", 1);
        assertNotEquals(personA, personB);
    }

    @Test
    void notEqualsAgeequalsTest(){
        Person personA = new Person("", 1);
        Person personB = new Person("", 12);
        assertNotEquals(personA, personB);
    }

}
