package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    public static Map<String, Integer> getMinMax(int[] arr) {
        Map<String, Integer> result = new HashMap<>();
        MaxThread maxThread = new MaxThread(arr, result);
        MinThread minThread = new MinThread(arr, result);

        maxThread.start();
        minThread.start();

        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
