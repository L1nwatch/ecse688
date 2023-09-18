public class Cycle {
    private final int radius;

    public Cycle(int radius) {
        this.radius = radius;
    }

    public static void main(String[] args) {
        Cycle c = new Cycle(3);
        double result = c.calculateArea();
        System.out.printf("Area = {%s}%n".formatted(result));
    }

    double calculateArea() {
        return Math.PI * radius * radius;
    }
}
