package me.javalessons;

public class TaskWithReentrantMethods implements Runnable{
    @Override
    public void run() {
        System.out.println("Run " + Thread.currentThread().getName());
        first();
    }

    private synchronized void first(){
        System.out.println("In first " + Thread.currentThread().getName());
        second();
    }

    private synchronized void second(){
        System.out.println("In second " + Thread.currentThread().getName());
    }
}
