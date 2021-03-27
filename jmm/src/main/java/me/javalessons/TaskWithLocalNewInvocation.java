package me.javalessons;

public class TaskWithLocalNewInvocation implements Runnable{
    @Override
    public void run() {
        Object value = new Object();
        System.out.println(value.toString());
    }
}
