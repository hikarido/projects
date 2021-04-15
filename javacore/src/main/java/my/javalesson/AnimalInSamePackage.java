package my.javalesson;

public class AnimalInSamePackage {
    public boolean callDogsProtectedMethod(){
        return new Dog().protectedMethod();
    }
}
