package me.javalessons;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Thread 1 locks lock 1
 * Thread 2 locks lock 2
 * thread 1 tries lock locks 2, thread 2 tries locks lock 1 (dead lock here)
 *
 * Find out about deadlock by ThreadMXBeanFactory
 */
public class DeadlockMaker {
    private Lock l1 = new ReentrantLock();
    private Lock l2 = new ReentrantLock();

    public void makeDeadLock() throws InterruptedException {
        final String lockOneName = "l1";
        final String lockTwoName = "l2";
        final TaskWhichDoLock taskWhichDoLockOne = new TaskWhichDoLock(this.l1, lockOneName, this.l2, lockTwoName);
        final Thread threadOne = new Thread(taskWhichDoLockOne, "threadOne");

        final TaskWhichDoLock taskWhichDoLockTwo = new TaskWhichDoLock(this.l2, lockTwoName, this.l1, lockOneName);
        final Thread threadTwo = new Thread(taskWhichDoLockTwo, "threadTwo");
        threadOne.start();
        threadTwo.start();


        while (true){
            threadOne.join(10);
            threadOne.join(10);
            Thread.sleep(100);
            System.out.println("Wait");

            ThreadMXBean tmx = ManagementFactory.getThreadMXBean();
            long[] ids = tmx.findDeadlockedThreads();
            if (ids != null) {
                ThreadInfo[] infos = tmx.getThreadInfo(ids, true, true);
                System.out.println("The following threads are deadlocked:");
                for (ThreadInfo ti : infos) {
                    System.out.println(ti);
                }
            }

            if(!threadOne.isAlive() & !threadTwo.isAlive()){
                break;
            }
        }
    }

    private static class TaskWhichDoLock implements Runnable{
        private final Lock lock1;
        private final Lock lock2;
        private final String lockOneName;
        private final String lockTwoName;

        private TaskWhichDoLock(Lock lock1, String lockOneName, Lock lock2, String lockTwoName) {
            this.lock1 = lock1;
            this.lockOneName = lockOneName;
            this.lock2 = lock2;
            this.lockTwoName = lockTwoName;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " doing acquisition of " + lockOneName);
                lock1.lock();
                System.out.println(Thread.currentThread().getName() + " " + lockOneName + " lock obtained");
                Thread.sleep(3000);

                System.out.println(Thread.currentThread().getName() + " doing acquisition of " + lockTwoName);
                lock2.lock();
                System.out.println(Thread.currentThread().getName() + " " + lockTwoName + " lock obtained");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
}
