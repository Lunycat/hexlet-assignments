package exercise;

// BEGIN
public class App {

    public static void printSquare(Circle circle) {
        try {
            System.out.print((int) Math.ceil(circle.getSquare()) + "\n");
        } catch (NegativeRadiusException e) {
            System.out.print("Не удалось посчитать площадь" + "\n");
        } finally {
            System.out.print("Вычисление окончено");
        }
    }
}
// END
