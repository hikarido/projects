package ru.java.learn;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class Chapter_6_3 {
    @Test
    void should_build(){
    Dish dish = Dish.builder().
                withCalories(100)
                .withCaloricLevel(CaloricLevel.DIET)
                .build();
    }

    @Test
    void should_group_dishes_by_caloric_level(){
    }
}
