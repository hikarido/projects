package me.javalesson;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {
    @Test
    void itShouldCreatesCorrectlyByDefaultConstructorTest(){
        Person p = new Person("hero", 10);
        assertTrue(p != null);

    }
}
