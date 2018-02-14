package frc.team281.tests.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.wpi.first.wpilibj.RobotTestUtils;
import frc.team281.subsystems.FakeDriveInstructionSource;
import frc.team281.subsystems.FakeDriveSubsystem;
import frc.team281.tests.BaseTest;

public class TestDriveSystemCommands extends BaseTest {

    protected FakeDriveSubsystem driveSubsystem;

    @Test
    public void testThatDriveSystemDrives() {

        // this is a fake joystick that simlulates the real one in the tests
        // initially the joystick is centered.
        FakeDriveInstructionSource testJoystick = new FakeDriveInstructionSource();

        // the subsystem creates a command for us-- no need to register it!
        driveSubsystem = new FakeDriveSubsystem(testJoystick);

        RobotTestUtils.runForSeconds(0.5);

        // should not have moved, joystick is centered
        assertEquals(driveSubsystem.getXPosition(), 0.0, 0.001);
        assertEquals(driveSubsystem.getYPosition(), 0.0, 0.001);

        testJoystick.setJoystickForward(1.0);

        RobotTestUtils.runForSeconds(0.5);

        // we should move about 10 units per second forward, ish
        assertEquals(driveSubsystem.getXPosition(), 5.0, 3.0);
        assertEquals(driveSubsystem.getYPosition(), 0.0, 0.001);

    }
}
