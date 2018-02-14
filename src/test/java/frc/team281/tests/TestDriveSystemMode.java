package frc.team281.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import frc.team281.robot.subsystems.DriveSystemMode;
import frc.team281.robot.subsystems.DriveSystemMode.StateResult;

public class TestDriveSystemMode extends BaseTest {

    protected DriveSystemMode mode;

    @Before
    public void setup() {
        mode = new DriveSystemMode();
    }

    @Test
    public void testInitialState() {

        assertTrue(mode.isDisabled());
        assertFalse(mode.isCalibrating());
    }

    @Test
    public void testCalibration() {
        assertFalse(mode.isCalibrated());
        StateResult r = mode.enterCalibrate();
        assertEquals(StateResult.ENTERED, r);
        assertTrue(mode.isCalibrating());
        assertEquals(StateResult.REJECTED, mode.enterPosition());
        assertEquals(StateResult.REJECTED, mode.enterSpeed());
        mode.finishCalibrating();
        assertTrue(mode.isCalibrated());
        assertEquals(StateResult.ENTERED, mode.enterPosition());
    }

    @Test
    public void testCantEnterPositionWithoutCalibration() {
        assertEquals(StateResult.REJECTED, mode.enterPosition());
        mode.enterCalibrate();
        mode.finishCalibrating();
        assertEquals(StateResult.ENTERED, mode.enterPosition());

    }

    @Test
    public void testFlippingBetweenSpeedAndPosition() {
        mode.enterCalibrate();
        mode.finishCalibrating();
        assertEquals(StateResult.ENTERED, mode.enterPosition());
        assertEquals(StateResult.ENTERED, mode.enterSpeed());
        assertEquals(StateResult.ENTERED, mode.enterPosition());
        assertEquals(StateResult.CURRENT, mode.enterPosition());
    }
}
