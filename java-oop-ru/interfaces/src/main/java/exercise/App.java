package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static void main(String[] args) {
        List<Home> list = new ArrayList<>(List.of(
                new Flat(41, 3, 10),
                new Cottage(125.5, 2),
                new Flat(80, 10, 2),
                new Cottage(150, 3)
        ));

        System.out.println(buildApartmentsList(list, 3));
    }

    public static List<String> buildApartmentsList(List<Home> homes, int limit) {
        return homes.stream()
                .sorted(Home::compareTo)
                .limit(limit)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
// END
