package me.javalessons;

public class TaskWithoutFields implements Runnable{

    @Override
    public void run() {
        System.out.println("It's me in: " + Thread.currentThread().getName());
        int i = 0;
        for(; i < 100; i++){
        }

        System.out.println(i);
    }
}
