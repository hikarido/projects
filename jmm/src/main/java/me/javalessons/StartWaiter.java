package me.javalessons;

public class StartWaiter implements Runnable{
    public void setReady(boolean ready) {
        this.ready = ready;
    }

    private boolean ready = false;

    @Override
    public void run() {
        while (!ready){
            System.out.println("Wait");
            Thread.yield();
        }

        while (ready){
            System.out.println("It's me");
        }
        System.out.println("Ready was set to false");
    }
}
