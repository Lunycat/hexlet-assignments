package exercise;

import java.util.Map;

public class MaxThread extends Thread {

    private final Map<String, Integer> map;
    private final int[] arr;

    public MaxThread(int[] arr, Map<String, Integer> map) {
        this.map = map;
        this.arr = arr;
    }

    @Override
    public void run() {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        map.put("max", max);
    }
}
