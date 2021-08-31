package ru.java.learn;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter_5_8_5 {
    @Test
    void shouldGenerateFirstFiveFibonacciNumbers(){
        Stream<ImmutablePair<Integer, Integer>> fibonaccis = Stream.iterate(
                ImmutablePair.of(0, 1),
                pair -> ImmutablePair.of(
                        pair.right,
                        pair.right + pair.left
                ));
        var collect = fibonaccis.limit(5).collect(Collectors.toList());
        ArrayList<Integer> result = new ArrayList<>(5);
        result.add(collect.get(0).left);
        collect.stream().forEach(pair -> result.add(pair.right));
        List<Integer> expect = List.of(0, 1, 1, 2, 3, 5);
        Assert.assertEquals(result, expect);
    }
}