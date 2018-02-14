package frc.team281.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import frc.team281.robot.controllers.EncoderCheck;

public class TestEncoderCheck {

    public static final int BIGGER_THAN_ZERO = 22;

    @Test
    public void testAllEncodersWorking() {

        // int leftRearCounts, int leftFrontCounts, int rightFrontCounts, int
        // rightRearCounts
        EncoderCheck ec = new EncoderCheck(BIGGER_THAN_ZERO, BIGGER_THAN_ZERO, BIGGER_THAN_ZERO, BIGGER_THAN_ZERO);

        assertTrue(ec.allOk());
        assertFalse(ec.hasProblems());
        assertTrue(ec.canDrive());
        assertTrue(ec.isLeftOk());
        assertTrue(ec.isRightOk());
        assertTrue(ec.isLeftRearOk());
        assertTrue(ec.isRightRearOk());
        assertTrue(ec.isLeftFrontOk());
        assertTrue(ec.isLeftRearOk());
        assertFalse(ec.shouldLeftRearFollowLeftFront());
        assertFalse(ec.shouldRightFrontFollowRightRear());
        assertFalse(ec.shouldRightRearFollowRightFront());
        assertFalse(ec.shouldLeftFrontFollowLeftRear());
    }

    @Test
    public void testLeftRearBroken() {
        EncoderCheck ec = new EncoderCheck(0, BIGGER_THAN_ZERO, BIGGER_THAN_ZERO, BIGGER_THAN_ZERO);

        assertFalse(ec.allOk());
        assertTrue(ec.canDrive());
        assertTrue(ec.isLeftOk());
        assertFalse(ec.isLeftRearOk());
        assertTrue(ec.isLeftFrontOk());
        assertTrue(ec.shouldLeftRearFollowLeftFront());
        assertFalse(ec.shouldRightFrontFollowRightRear());
        assertFalse(ec.shouldRightRearFollowRightFront());
        assertFalse(ec.shouldLeftFrontFollowLeftRear());
    }

    @Test
    public void testRightFrontBroken() {
        EncoderCheck ec = new EncoderCheck(BIGGER_THAN_ZERO, BIGGER_THAN_ZERO, 0, BIGGER_THAN_ZERO);

        assertFalse(ec.allOk());
        assertTrue(ec.canDrive());
        assertTrue(ec.isRightOk());
        assertFalse(ec.isRightFrontOk());
        assertTrue(ec.isRightRearOk());
        assertFalse(ec.shouldLeftRearFollowLeftFront());
        assertTrue(ec.shouldRightFrontFollowRightRear());
        assertFalse(ec.shouldRightRearFollowRightFront());
        assertFalse(ec.shouldLeftFrontFollowLeftRear());
    }

    @Test
    public void testBothLeftBroken() {
        EncoderCheck ec = new EncoderCheck(0, 0, BIGGER_THAN_ZERO, BIGGER_THAN_ZERO);

        assertFalse(ec.allOk());
        assertFalse(ec.canDrive());
        assertTrue(ec.isRightOk());
        assertFalse(ec.isLeftOk());
        assertTrue(ec.isRightRearOk());
        assertFalse(ec.isLeftFrontOk());
        assertFalse(ec.isLeftRearOk());
        assertFalse(ec.shouldLeftRearFollowLeftFront());
        assertFalse(ec.shouldRightFrontFollowRightRear());
        assertFalse(ec.shouldRightRearFollowRightFront());
        assertFalse(ec.shouldLeftFrontFollowLeftRear());
    }
}
