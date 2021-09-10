package ru.java.learn;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Chapter_6_3 {

    private static final Map<String, List<String>> dishTags = ofEntries(
            entry("pork", asList("greasy", "salty")),
            entry("beef", asList("salty", "roasted")),
            entry("chicken", asList("fried", "crisp")),
            entry("french fries", asList("greasy", "fried")),
            entry("rice", asList("light", "natural")),
            entry("season fruit", asList("fresh", "natural")),
            entry("pizza", asList("tasty", "salty")),
            entry("prawns", asList("tasty", "roasted")),
            entry("salmon", asList("delicious", "fresh"))
    );
    private Dish pork = Dish.builder().
            withName("pork").withIsVegetarian(false).withCalories(800).withType(Dish.Type.MEAT).build();
    private Dish beef = Dish.builder()
            .withName("beef").withIsVegetarian(false).withCalories(700).withType(Dish.Type.MEAT).build();
    private Dish chicken =
            Dish.builder().withName("chicken").withIsVegetarian(false).withCalories(400).withType(Dish.Type.MEAT).build();
    private Dish frenchFries = Dish.builder()
            .withName("french fries").withIsVegetarian(true).withCalories(530).withType(Dish.Type.OTHER).build();
    private Dish rice = Dish.builder()
            .withName("rice").withIsVegetarian(true).withCalories(350).withType(Dish.Type.OTHER).build();
    private Dish seasonFruit = Dish.builder()
            .withName("season fruit").withIsVegetarian(true).withCalories(120).withType(Dish.Type.OTHER).build();
    private Dish pizza = Dish.builder()
            .withName("pizza").withIsVegetarian(true).withCalories(550).withType(Dish.Type.OTHER).build();
    private Dish prawns = Dish.builder()
            .withName("prawns").withIsVegetarian(false).withCalories(400).withType(Dish.Type.FISH).build();
    private Dish salmon = Dish.builder()
            .withName("salmon").withIsVegetarian(false).withCalories(450).withType(Dish.Type.FISH).build();

    private final List<Dish> menu = asList(
            pork,
            beef,
            chicken,
            frenchFries,
            rice,
            seasonFruit,
            pizza,
            prawns,
            salmon
    );

    @Test
    void should_build() {
        Dish dish = Dish.builder().
                withCalories(100)
                .withType(Dish.Type.FISH)
                .withIsVegetarian(true)
                .withName("dish")
                .build();
    }

    @Test
    void should_group_dishes_by_type_and_filter_dishes_calories_of_which_is_more_then_500() {
        Map<Dish.Type, Set<Dish>> actual = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(Collectors.groupingBy(Dish::getType, Collectors.toSet()));

        Map<Dish.Type, Set<Dish>> expected = ofEntries(
                entry(Dish.Type.MEAT, Set.of(pork, beef)),
                entry(Dish.Type.OTHER, Set.of(frenchFries, pizza)));

        assertThat(actual, equalTo(expected));
    }

    @Test
    void should_group_dishes_filtered_by_calories_more_then_500_by_type_and_fish_type_should_be_presented() {
        final Map<Dish.Type, Set<Dish>> actual = menu.stream().collect(
                Collectors.groupingBy(
                        Dish::getType,
                        Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toSet())));
        Map<Dish.Type, Set<Dish>> expected = ofEntries(
                entry(Dish.Type.MEAT, Set.of(pork, beef)),
                entry(Dish.Type.OTHER, Set.of(frenchFries, pizza)),
                entry(Dish.Type.FISH, Collections.emptySet()));
        assertThat(actual, equalTo(expected));
    }

    @Test
    void should_group_dish_names_by_type() {
        Map<Dish.Type, Set<String>> actual = menu.stream().
                collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(Dish::getName, Collectors.toSet())));

        Map<Dish.Type, Set<Dish>> almostExpected = ofEntries(entry(Dish.Type.MEAT, Set.of(pork, beef, chicken)),
                entry(Dish.Type.OTHER, Set.of(frenchFries, seasonFruit, pizza, rice)),
                entry(Dish.Type.FISH, Set.of(prawns, salmon)));
        Map<Dish.Type, Set<String>> expected = almostExpected.entrySet().stream()
                .map(entry -> entry(entry.getKey(),
                        entry.getValue().stream()
                                .map(dish -> dish.getName())
                                .collect(Collectors.toSet()))
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        assertThat(actual, equalTo(expected));
    }
}
