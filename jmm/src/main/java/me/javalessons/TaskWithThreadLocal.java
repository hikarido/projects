package me.javalessons;

public class TaskWithThreadLocal implements Runnable{
    private ThreadLocal<String> localValue = new ThreadLocal<>();
    @Override
    public void run() {
        localValue.set(Thread.currentThread().getName());
        System.out.println(localValue.get());
    }
}
