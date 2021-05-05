package my.javalesson;


import my.javalesson.jbej.item24.WithNonStaticPublicInnerClass;
import my.javalesson.jbej.item24.WithStaticInerClass;
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

    @Test
    void createOuterAndNonStaticInnerClassInctancesAndGetAccessToThem() {
        WithNonStaticPublicInnerClass outer = new WithNonStaticPublicInnerClass(10);
        WithNonStaticPublicInnerClass.Inner inner = outer.new Inner();
        System.out.println(inner.getOuter());
        System.out.println(inner);
    }

    @Test
    void createOuterAndStaticInnerClassInstancesAndUseThem() {
        WithStaticInerClass enclosing = new WithStaticInerClass(10);
        WithStaticInerClass.Inner inner = new WithStaticInerClass.Inner(11);
        System.out.println(enclosing);
        System.out.println(inner);
    }
}
