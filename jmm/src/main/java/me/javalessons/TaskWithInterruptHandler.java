package me.javalessons;

public class TaskWithInterruptHandler implements Runnable{

    @Override
    public void run() {
        boolean wasInterrupted = false;
        while (true){
            System.out.println("It's me");
            if(wasInterrupted){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Someone interrupts me again! Ok, i am stopping!");
                    break;
                }
            }
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Someone interrupted me, but i ignore it");
                wasInterrupted = true;
            }
        }

        System.out.println("My end");
    }
}
