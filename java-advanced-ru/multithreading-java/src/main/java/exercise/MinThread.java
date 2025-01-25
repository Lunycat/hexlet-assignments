package exercise;

import java.util.Map;

public class MinThread extends Thread {

    private final Map<String, Integer> map;
    private final int[] arr;

    public MinThread(int[] arr, Map<String, Integer> map) {
        this.map = map;
        this.arr = arr;
    }

    @Override
    public void run() {
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }

        map.put("min", min);
    }
}
