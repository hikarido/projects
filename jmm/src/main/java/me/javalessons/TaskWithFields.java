package me.javalessons;

public class TaskWithFields implements Runnable {
    private final int limit;
    private int value;

    public TaskWithFields(int limit) {
        this.limit = limit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void run() {
        for (int i = 0; i < limit; i++) {
            value++;
        }
    }
}
