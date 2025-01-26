package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {

    private final List<Integer> list = new ArrayList<>();

    public synchronized void add(int element) {
        list.add(element);
    }

    public int get(int index) {
        return list.get(index);
    }

    public int getSize() {
        return list.size();
    }
}
