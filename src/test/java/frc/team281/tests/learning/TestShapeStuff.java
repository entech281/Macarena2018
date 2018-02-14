package frc.team281.tests.learning;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import frc.team281.shapes.Circle;
import frc.team281.shapes.HasPerimeter;
import frc.team281.shapes.Rectangle;
import frc.team281.shapes.Shape;
import frc.team281.shapes.Square;

public class TestShapeStuff {

    @Test
    public void testShapes() {
        HasPerimeter s = new Rectangle(1.0, 3.0);
        HasPerimeter s2 = new Circle(1.0);
        double total = addPerimeters(s2, s);
        assertEquals(14.28, total, 0.1);
    }

    @Test
    public void assertSquareWorks() {
        Shape s = new Square(2.0);
        assertEquals(8.0, s.getPerimeter(), 0.00000001);
    }

    public double addPerimeters(HasPerimeter... perimeters) {
        double the_total = 0.0;
        for (HasPerimeter hp : perimeters) {
            the_total += hp.getPerimeter();
        }
        return the_total;
    }

}
