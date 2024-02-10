package exercise;

// BEGIN
class Flat implements Home {
    private final int floor;
    private final double totalArea;

    public Flat(double area, double balconyArea, int floor) {
        this.floor = floor;
        this.totalArea = area + balconyArea;
    }

    @Override
    public double getArea() {
        return totalArea;
    }

    @Override
    public int compareTo(Home home) {
        if (this.totalArea < home.getArea()) {
            return -1;
        } else if (this.totalArea > home.getArea()) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        return "Квартира площадью " + totalArea + " метров на " + floor + " этаже";
    }
}
// END
