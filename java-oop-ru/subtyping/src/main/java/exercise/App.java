package exercise;

import java.util.Map;
import java.util.Set;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> map = storage.toMap();
        Set<String> keys = map.keySet();

        keys.forEach(k -> {
            storage.set(storage.get(k, ""), k);
            storage.unset(k);
        });
    }
}
// END
