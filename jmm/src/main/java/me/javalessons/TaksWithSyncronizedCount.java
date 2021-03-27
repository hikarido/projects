package me.javalessons;

public class TaksWithSyncronizedCount implements Runnable{
    private int count;
    private final int limit;

    public TaksWithSyncronizedCount(int limit) {
        this.limit = limit;
    }

    @Override
    public void run() {
        synchronized (this){
            for(int i =0; i < limit; i++){
                count++;
                System.out.println(Thread.currentThread().getName());
            }
    }
    }

    public int getCount() {
        return count;
    }
}
