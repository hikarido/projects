package me.javalessons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void getThreadID() {
        final String currentThreadInfo = Thread.currentThread().getId() + " " + Thread.currentThread().getName();
        System.out.println(currentThreadInfo);
    }

    @Test
    public void getThreadsCount() {
        System.out.println(Thread.activeCount());
    }

    @Test
    public void getAllThreadsInfo() {
        Thread tr = new Thread("Mine");
        tr.start();
        final int threadsCount = Thread.activeCount();
        Thread[] threads = new Thread[threadsCount];
        final int retvalue = Thread.enumerate(threads);
        System.out.println("count: " + threadsCount);
        System.out.println("retvalue: " + retvalue);
        for (final Thread t : threads) {
            System.out.printf("id %d, name %s, priority %d, state %s%n",
                    t.getId(),
                    t.getName(),
                    t.getPriority(),
                    t.getState()
            );
        }
    }

    @Test
    public void runsTaskInThread() throws InterruptedException {
        TaskWithoutFields task = new TaskWithoutFields();
        Thread t = new Thread(task, "task owner");
        t.start();
        t.join();
    }

    @Test
    public void runsTaskInThreads() throws InterruptedException {
        TaskWithoutFields task = new TaskWithoutFields();
        Thread t = new Thread(task, "task owner 1");
        Thread t2 = new Thread(task, "task owner 2");
        t.start();
        t2.start();
        t.join();
        t2.join();
    }

    @Test
    public void runTaskWithLocalNewInvocationInSeveralThreads000objectAddressesMustBeDifferent() throws InterruptedException {
        final TaskWithLocalNewInvocation taskWithLocalNewInvocation = new TaskWithLocalNewInvocation();
        final Thread a = new Thread(taskWithLocalNewInvocation, "a");
        final Thread b = new Thread(taskWithLocalNewInvocation, "b");
        a.start();
        b.start();
        a.join();
        b.join();
    }

    @Test
    public void runTasksWithLocalNewInvocationInSeveralThreads000objectAddressesMustBeDifferent() throws InterruptedException {
        final TaskWithLocalNewInvocation taskWithLocalNewInvocation = new TaskWithLocalNewInvocation();
        final TaskWithLocalNewInvocation taskWithLocalNewInvocation1 = new TaskWithLocalNewInvocation();
        final Thread a = new Thread(taskWithLocalNewInvocation, "a");
        final Thread b = new Thread(taskWithLocalNewInvocation1, "b");
        a.start();
        b.start();
        a.join();
        b.join();
    }

    @Test
    public void countValueInOneRunnableBySeveralThreads() throws InterruptedException {
        final int expected = 1000;
        final TaskWithFields taskWithFields = new TaskWithFields(expected);
        Thread a = new Thread(taskWithFields, "a");
        Thread b = new Thread(taskWithFields, "b");
        a.start();
        b.start();
        a.join();
        b.join();
        Assertions.assertFalse(
                taskWithFields.getValue() == expected);
    }

    @Test
    public void countValueInTwoRunnableBySeveralThreads() throws InterruptedException {
        final int expected = 1000;
        final int expected1 = 10_123;
        final TaskWithFields taskWithFields = new TaskWithFields(expected);
        final TaskWithFields taskWithFields1 = new TaskWithFields(expected1);
        Thread a = new Thread(taskWithFields, "a");
        Thread b = new Thread(taskWithFields1, "b");
        a.start();
        b.start();
        a.join();
        b.join();
        Assertions.assertEquals(expected, taskWithFields.getValue());
        Assertions.assertEquals(expected1, taskWithFields1.getValue());
    }

    @Test
    public void twoThreadAndOneVolatileVariable() throws InterruptedException {
        final int limit = 1_000_000;
        final TaskWithVolatileCount taskWithVolatileCount = new TaskWithVolatileCount(limit);
        final Thread threadA = new Thread(taskWithVolatileCount, "a");
        final Thread threadB = new Thread(taskWithVolatileCount, "b");

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();

        System.out.println(taskWithVolatileCount.getValue());
    }

    @Test
    public void threadsWithSyncronisation() throws InterruptedException {
        final int limit = 1_000_000;
        final TaksWithSyncronizedCount task = new TaksWithSyncronizedCount(limit);
        final Thread threadA = new Thread(task, "a");
        final Thread threadB = new Thread(task, "b");

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();

        System.out.println(task.getCount());
    }

    @Test
    public void interruptsOtherThread() throws InterruptedException {
        final TaskWithInterruptHandler taskWithInterruptHandler = new TaskWithInterruptHandler();
        final Thread thread = new Thread(taskWithInterruptHandler);
        thread.start();

        Thread.sleep(10);
        thread.interrupt();
        thread.interrupt();
    }

    @Test
    public void runAndSetReady() throws InterruptedException {
        final StartWaiter startWaiter = new StartWaiter();
        final Thread thread = new Thread(startWaiter);
        thread.start();
        Thread.sleep(10);
        startWaiter.setReady(true);
        Thread.sleep(10);
        startWaiter.setReady(false);
        thread.join();
    }

    @Test
    public void goToreentrantSyncBlocks() throws InterruptedException {
        final TaskWithReentrantMethods taskWithReentrantMethods = new TaskWithReentrantMethods();
        final Thread thread1 = new Thread(taskWithReentrantMethods, "1");
        final Thread thread2 = new Thread(taskWithReentrantMethods, "2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    @Test
    public void volatileCountIsNotEnough() throws InterruptedException {
        final VolatileCounter volatileCounter = new VolatileCounter();
        final Thread firstThread = new Thread(volatileCounter, "firstThread");
        final Thread secondThread = new Thread(volatileCounter, "secondThread");
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
    }

    @Test
    public void threadLocalMustBeDifferentInDistinctThreads() throws InterruptedException {
        final TaskWithThreadLocal taskWithThreadLocal = new TaskWithThreadLocal();
        final Thread one = new Thread(taskWithThreadLocal, "One");
        final Thread two = new Thread(taskWithThreadLocal, "two");
        one.start();
        two.start();
        one.join();
        two.join();
    }

    @Test
    public void threadLocalInThreadPool() throws InterruptedException {
        final TaskWithThreadLocal task1 = new TaskWithThreadLocal();
        final TaskWithThreadLocal task2 = new TaskWithThreadLocal();
        final TaskWithThreadLocal tast3 = new TaskWithThreadLocal();
        final TaskWithThreadLocal task4 = new TaskWithThreadLocal();
        final Thread one = new Thread("One");
        final Thread two = new Thread( "two");

        one.start();
        two.start();
        one.join();
        two.join();
    }

    @Test
    public void makeDeadLockForMeBaby() throws InterruptedException {
        new DeadlockMaker().makeDeadLock();
    }

    @Test
    public void deadlockOrderResolution() throws InterruptedException {
        new DeadlockPreventionByReordering().makeDeadLock();
    }
}
