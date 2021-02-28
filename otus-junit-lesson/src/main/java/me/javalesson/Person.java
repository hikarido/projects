package me.javalesson;

public class Person {
    private int age;
    private String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != getClass()) {
            return false;
        }

        Person o = (Person) other;
        return o == this || (o.getAge() == getAge() && o.getName().equals(getName()));

    }

    public void alwaysThrowsException() {
        throw new RuntimeException("It's me!");
    }
}
