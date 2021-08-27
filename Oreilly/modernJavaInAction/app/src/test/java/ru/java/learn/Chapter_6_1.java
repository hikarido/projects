package ru.java.learn;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Chapter_6_1 {

    private final Trader raoul = new Trader("Raoul", "Cambrige");
    private final Trader mario = new Trader("Mario", "Milan");
    private final Trader alan = new Trader("Alan", "Cambrige");
    private final Trader brian = new Trader("Brian", "Cambrige");

    private final List<Trader> traders = List.of(raoul, mario, alan, brian);

    private final List<Transaction> transactions = List.of(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950));

    @Test()
    void findAllTransactionsInTheYear2011andSortThemByValue() {
        List<Transaction> answer = transactions.stream().
                filter(v -> v.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        List<Transaction> expected = List.of(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2011, 400)
        );
        Assert.assertTrue(answer.equals(expected));
    }

    @Test
    void whatAreAllTheUniqueueCituesWhereTradersWork() {
        Set<String> answer = traders.stream().map(Trader::getCity).distinct().collect(Collectors.toSet());
        final Set<String> uniqueCities = Set.of("Cambrige", "Milan");
        Assert.assertEquals(answer, uniqueCities);
    }

    @Test
    void findAllTradersFromCambrigeAndSortThemByName() {
        List<Trader> answer = traders.stream().
                filter(trader -> trader.getCity().equals("Cambrige"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        Assert.assertTrue(answer.equals(List.of(alan, brian, raoul)));
    }

    @Test
    void returnAStringOfAllTradersNamesSortedAlphabetically() {
        String names = traders.stream()
                .map(Trader::getName)
                .sorted()
                .distinct()
                .collect(Collectors.joining());
        Assert.assertEquals(names, "AlanBrianMarioRaoul");
    }

    @Test
    void areAnyTradersBasedInMilan() {
        boolean answer = traders.stream()
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        Assert.assertTrue(answer);
    }

    @Test
    void printTheValuesOfAllTransactionsFromTheTradersLivingInCambridge() {
        List<Integer> answer = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambrige"))
                .map(Transaction::getValue)
                .sorted()
                .collect(Collectors.toList());
        Assert.assertTrue(answer.equals(List.of(300, 400, 950, 1000)));
    }

    @Test
    void whatTheHighestValueOfAllTransactions() {
        Integer max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max).orElseThrow();

        Assert.assertEquals(max.intValue(), 1000);
    }

    @Test
    void findTheTransactionWithSmallestValue() {
        Transaction answer = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .orElseThrow();

        Assert.assertEquals(answer, new Transaction(brian, 2011, 300));
    }

}
