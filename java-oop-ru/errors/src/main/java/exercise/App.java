package exercise;

// BEGIN
class App {
    public static void printSquare(Circle circle) {
        try {
            int circleArea = (int) Math.round(circle.getSquare());
            System.out.println(circleArea);
            System.out.println("Вычисление окончено");
        } catch (NegativeRadiusException negativeRadiusExeption) {
            System.out.println("Не удалось посчитать площадь");
            System.out.println("Вычисление окончено");
        }
    }
}
// END
