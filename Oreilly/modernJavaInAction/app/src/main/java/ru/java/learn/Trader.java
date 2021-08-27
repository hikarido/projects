package ru.java.learn;

import java.io.ObjectStreamClass;
import java.util.Objects;

public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format("{name: %s, city: %s}", getName(), getCity());
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }

        if(!(other instanceof Trader)){
            return false;
        }

        Trader o = (Trader) other;
        return Objects.equals(getName(), o.getName()) &&
                Objects.equals(getCity(), o.getCity());
    }
}
