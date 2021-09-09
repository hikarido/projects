package ru.java.learn;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Dish {

    @Nonnull
    private final Integer calories;

    @Nonnull
    private final CaloricLevel caloricLevel;

    private Dish(@Nonnull Integer calories, @Nonnull CaloricLevel caloricLevel) {
        this.calories = Objects.requireNonNull(calories, "calories");
        this.caloricLevel = Objects.requireNonNull(caloricLevel, "caloricLevel");
    }

    @Nonnull
    public Integer getCalories() {
        return calories;
    }

    @Nonnull
    public CaloricLevel getCaloricLevel() {
        return caloricLevel;
    }

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }

        if(!(other instanceof Dish)){
            return false;
        }

        Dish dish = (Dish)other;
        return Objects.equals(getCalories(), dish.getCalories()) &&
                Objects.equals(getCaloricLevel(), dish.getCaloricLevel());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getCalories(), getCaloricLevel());
    }

    @Nonnull
    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder{
        @Nonnull
        private Integer calories;

        @Nonnull
        private CaloricLevel caloricLevel;

        private Builder(){

        }

        public Builder withCalories(@Nonnull Integer calories){
            this.calories = calories;
            return this;
        }

        public Builder withCaloricLevel(@Nonnull CaloricLevel caloricLevel){
            this.caloricLevel = caloricLevel;
            return this;
        }

        public Dish build(){
            return new Dish(calories, caloricLevel);
        }
    }
}
