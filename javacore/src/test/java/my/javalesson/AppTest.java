package my.javalesson;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void createAnimalAndUseCtor()
    {
        final Animal animal = new Animal();
    }

    @Test
    void createDog() {
        new Dog();
    }

    @Test
    void callStaticMethodOfDog() {
        Dog.staticMethod();
    }

    @Test
    void callDogsProtectedMethodFromOtherClass() {
        Assertions.assertTrue(new AnimalInSamePackage().callDogsProtectedMethod());
    }
}
