package frc.team281.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import frc.team281.robot.OperatorInterface;

public class TestOperatorInterface {

    @Test
    public void testJoystickSoftness() {
        // public static double adjustJoystickSoftness(double softnessFactor, double
        // rawValue)
        assertEquals(1.0, OperatorInterface.adjustJoystickSoftness(0.5, 1.0), 0.01);
        assertEquals(1.0, OperatorInterface.adjustJoystickSoftness(0, 0.5), 0.01);
        assertEquals(0.5625, OperatorInterface.adjustJoystickSoftness(2.0, 0.75), 0.01);
    }
}
