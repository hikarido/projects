package me.javalessons;

public class TaskWithVolatileCount implements Runnable {
    private final int limit;
    private volatile int value;

    public TaskWithVolatileCount(int limit) {
        this.limit = limit;
        this.value = 0;
    }

    @Override
    public void run() {
        int i = 0;
        for (; i < limit; i++) {
            value+=1;
        }

        System.out.println(Thread.currentThread().getName() + " " + value + " i: " + i);
    }

    public int getValue() {
        return value;
    }
}
