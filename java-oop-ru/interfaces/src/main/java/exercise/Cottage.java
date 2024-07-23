package exercise;

// BEGIN
public class Cottage implements Home {

    private final double area;
    private final int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home home) {
        return Double.compare(this.getArea(), home.getArea());
    }

    @Override
    public String toString() {
        return String.format("%s этажный коттедж площадью %s метров", floorCount, area);
    }
}
// END
