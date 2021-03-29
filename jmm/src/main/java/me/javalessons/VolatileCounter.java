package me.javalessons;

/**
 * At my PC it writes always 1_000_000 but it is not secure and not robust by jmm
 */
public class VolatileCounter implements Runnable {
    private volatile int count = 0;
    @Override
    public void run() {
        while (incrementCount()) {
        }

        System.out.printf("[thread name: %s] count: %d END%n", Thread.currentThread().getName(), count);
    }

    private boolean incrementCount() {
        if (count == 1_000_000) {
            return false;
        }
        count++;

        return true;
    }
}
