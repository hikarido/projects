package me.javalessons;

public class TaskRunner {

    private static int number = 0;
    private static boolean ready = false;

    private static class Reader extends Thread {

        @Override
        public void run() {
            System.out.println("in");
            while (!ready) {
                System.out.println("wait");
                Thread.yield();
            }

            System.out.println("after");
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Reader reader = new Reader();
        reader.start();
        Thread.sleep(10);
        number = 42;
        ready = true;

        reader.join();

    }
}
