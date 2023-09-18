public class Cycle {
    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int radius;
    private boolean allowRun = false;

    boolean isAllowRun() {
        return allowRun;
    }


    public static void main(String[] args) {
        Cycle c = new Cycle();
        c.radius = 233;
        double result = c.calculateArea();
        System.out.printf("Area = {%s}%n".formatted(result));
    }

    public double calculateArea() {
        if (isAllowRun()) {
            return 3.3;
        } else {
            return Math.PI * radius * radius;
        }
    }
}
