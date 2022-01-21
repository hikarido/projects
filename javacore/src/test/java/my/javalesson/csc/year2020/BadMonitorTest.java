package my.javalesson.csc.year2020;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

/**
 * Пример того, как много потоков делят один ресурс
 * https://youtu.be/5pTrGNYr_wg?list=PLOFtRFZ_HEbIyfsPWU4kcbFiYAGR2r_dG&t=2924
 */
public class BadMonitorTest {
    @Test
    public void should_be_undefined_behaviour_in_concurrent_environment() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        executeParallelAddWithManyMonitors(list);
        assertThat(list.size(), not(equalTo(1_000_000)));
        System.out.println(list.size());
    }

    @Test
    public void should_not_be_undefined_behaviour_in_concurrent_environment() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        BadMonitor monitor = new BadMonitor(list);
        executeParallelAddWithOneMonitor(monitor);
        assertThat(list.size(), equalTo(1_000_000));
        System.out.println(list.size());
    }

    private void executeParallelAddWithOneMonitor(BadMonitor monitor) throws InterruptedException {
        Runnable r = () -> {
            List<Integer> values = IntStream.rangeClosed(1, 100_000)
                    .boxed()
                    .collect(Collectors.toList());
            for (Integer value : values) {
                monitor.add(value);
            }
        };

        List<Thread> threads = Stream.generate(() -> new Thread(r))
                .limit(10)
                .collect(Collectors.toList());

        threads.stream().forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private void executeParallelAddWithManyMonitors(List<Integer> list) throws InterruptedException {
        Runnable r = () -> {
            BadMonitor monitor = new BadMonitor(list);
            List<Integer> values = IntStream.rangeClosed(1, 100_000)
                    .boxed()
                    .collect(Collectors.toList());
            for (Integer value : values) {
                monitor.add(value);
            }
        };

        List<Thread> threads = Stream.generate(() -> new Thread(r))
                .limit(10)
                .collect(Collectors.toList());

        threads.stream().forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
    }

}