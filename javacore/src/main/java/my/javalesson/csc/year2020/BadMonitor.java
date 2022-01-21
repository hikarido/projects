package my.javalesson.csc.year2020;

import java.util.ArrayList;
import java.util.List;

public class BadMonitor {
    private final List<Integer> list;

    public BadMonitor(List<Integer> list) {
        this.list = list;
    }

    public synchronized void add(Integer element) {
        list.add(element);
    }

    public int getListSize() {
        return list.size();
    }
}