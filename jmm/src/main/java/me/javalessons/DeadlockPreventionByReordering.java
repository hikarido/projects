package me.javalessons;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Thread 1 locks lock 1
 * Thread 2 locks lock 2
 * thread 1 tries lock locks 2, thread 2 tries locks lock 1 (dead lock here)
 */
public class DeadlockPreventionByReordering {
    private LockWithOrder l1 = new LockWithOrder(10, "Ten", null);
    private LockWithOrder l2 = new LockWithOrder(5, "Five", null);

    public void makeDeadLock() throws InterruptedException {
        final TaskWhichDoLock taskWhichDoLockOne = new TaskWhichDoLock(this.l1, this.l2);
        final Thread threadOne = new Thread(taskWhichDoLockOne, "threadOne");

        final TaskWhichDoLock taskWhichDoLockTwo = new TaskWhichDoLock(this.l2, this.l1);
        final Thread threadTwo = new Thread(taskWhichDoLockTwo, "threadTwo");
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadOne.join();
    }

    private static class TaskWhichDoLock implements Runnable {
        private final LockWithOrder lock1;
        private final LockWithOrder lock2;

        private TaskWhichDoLock(LockWithOrder lock1, LockWithOrder lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            try {
                LockWithOrder lockFirst = Collections.min(List.of(lock1, lock2), lock1.getComparator());
                LockWithOrder lockSecond = Collections.max(List.of(lock1, lock2), lock1.getComparator());
                System.out.println(Thread.currentThread().getName() + " doing acquisition of " + lockFirst);
                lockFirst.lock();
                System.out.println(Thread.currentThread().getName() + " " + lockFirst + " lock obtained");
                Thread.sleep(3000);

                System.out.println(Thread.currentThread().getName() + " doing acquisition of " + lockSecond);
                lockSecond.lock();
                System.out.println(Thread.currentThread().getName() + " " + lockSecond + " lock obtained");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    private static class LockWithOrder implements Lock {
        private final Lock lock = new ReentrantLock();
        private final long order;
        private final Comparator<LockWithOrder> comparator;
        private final String name;

        public LockWithOrder(long order, String name, Comparator<LockWithOrder> comparator) {
            this.order = order;
            this.name = name;
            if (comparator == null) {
                this.comparator = (LockWithOrder a, LockWithOrder b) -> {
                    return Long.compare(a.order, b.order);
                };
            } else {
                this.comparator = comparator;
            }
        }

        @Override
        public String toString() {
            return name;
        }

        public Comparator<LockWithOrder> getComparator(){
            return comparator;
        }

        @Override
        public void lock() {
            lock.lock();
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            lock.lockInterruptibly();
        }

        @Override
        public boolean tryLock() {
            return lock.tryLock();
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return lock.tryLock(time, unit);
        }

        @Override
        public void unlock() {
            lock.unlock();
        }

        @Override
        public Condition newCondition() {
            return lock.newCondition();
        }
    }
}
