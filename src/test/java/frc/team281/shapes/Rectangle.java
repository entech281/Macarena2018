package frc.team281.shapes;

public class Rectangle extends Shape {

    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getPerimeter() {
        return 2.0 * length + 2.0 * width;
    }

    @Override
    public double getArea() {
        // TODO Auto-generated method stub
        return 0;
    }
}
