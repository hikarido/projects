package ru.java.learn;

import javax.annotation.Nonnull;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Dish {

    @Nonnull
    private final String name;

    @Nonnull
    private final Boolean isVegetarian;

    @Nonnull
    private final Integer calories;

    @Nonnull
    private final Type type;

    private Dish(@Nonnull String name,
                @Nonnull Boolean isVegetarian,
                @Nonnull Integer calories,
                @Nonnull Type type) {
        this.name = requireNonNull(name, "name");
        this.isVegetarian = requireNonNull(isVegetarian, "isVegetarian");
        this.calories = requireNonNull(calories, "calories");
        this.type = requireNonNull(type, "type");
    }

    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    @Nonnull
    public Builder toBuilder(){
        return this.builder()
                .withName(getName())
                .withIsVegetarian(isVegetarian())
                .withCalories(getCalories())
                .withType(getType());
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public Boolean isVegetarian() {
        return isVegetarian;
    }

    @Nonnull
    public Integer getCalories() {
        return calories;
    }

    @Nonnull
    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Dish)) {
            return false;
        }

        Dish dish = (Dish) other;
        return Objects.equals(getName(), dish.getName()) &&
                Objects.equals(isVegetarian(), dish.isVegetarian()) &&
                Objects.equals(getCalories(), dish.getCalories()) &&
                Objects.equals(getType(), dish.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), isVegetarian(), getCalories(), getType());
    }

    public static final class Builder {
        @Nonnull
        private String name;

        @Nonnull
        private Boolean isVegetarian;

        @Nonnull
        private Integer calories;

        @Nonnull
        private Type type;

        private Builder() {

        }

        public Builder withName(@Nonnull String name){
            this.name = name;
            return this;
        }

        public Builder withIsVegetarian(@Nonnull Boolean isVegetarian){
            this.isVegetarian = isVegetarian;
            return this;
        }

        public Builder withCalories(@Nonnull Integer calories) {
            this.calories = calories;
            return this;
        }

        public Builder withType(@Nonnull Type type) {
            this.type = type;
            return this;
        }

        public Dish build() {
            return new Dish(name, isVegetarian, calories, type);
        }
    }

    public enum Type {
        MEAT,
        FISH,
        OTHER
    }
}
