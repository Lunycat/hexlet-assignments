package exercise;

import java.lang.reflect.Field;
import java.util.*;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> result = new ArrayList<>();

        for (Field field : address.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.isAnnotationPresent(NotNull.class)
                        && Objects.isNull(field.get(address))) {
                    result.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> result = new LinkedHashMap<>();
        List<String> list;

        for (Field field : address.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(address);
                if (field.isAnnotationPresent(NotNull.class) && Objects.isNull(fieldValue)) {
                    list = new ArrayList<>(List.of("can not be null"));
                    result.put(field.getName(), list);

                } else if (field.isAnnotationPresent(MinLength.class)) {
                    MinLength minLength = field.getAnnotation(MinLength.class);

                    if (fieldValue.toString().length() < minLength.minLength()) {
                        list = new ArrayList<>(List.of("length less than " + minLength.minLength()));
                        result.put(field.getName(), list);
                    }
                }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
        }
        return result;
    }
}
// END
