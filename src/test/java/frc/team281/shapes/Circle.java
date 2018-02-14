package frc.team281.shapes;

public class Circle extends Shape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getPerimeter() {
        return Math.PI * 2.0 * radius;
    }

    @Override
    public double getArea() {
        // TODO Auto-generated method stub
        return 0;
    }
}
