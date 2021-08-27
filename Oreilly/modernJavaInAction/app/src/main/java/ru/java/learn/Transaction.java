package ru.java.learn;

import java.util.Objects;

public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("{trader: %s}, year: %s, value: %s", trader, year, value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Transaction)) {
            return false;
        }

        Transaction o = (Transaction) other;

        return Objects.equals(getTrader(), o.getTrader()) &&
                Objects.equals(getYear(), o.getYear()) &&
                Objects.equals(getValue(), o.getValue());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getTrader(), getYear(), getValue());
    }
}
