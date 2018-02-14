package frc.team281.subsystems;

import frc.team281.robot.DriveInstruction;
import frc.team281.robot.DriveInstructionSource;

public class FakeDriveInstructionSource implements DriveInstructionSource {

    public double getJoystickLateral() {
        return joystickLateral;
    }

    public void setJoystickLateral(double joystickLateral) {
        this.joystickLateral = joystickLateral;
    }

    public double getJoystickForward() {
        return joystickForward;
    }

    public void setJoystickForward(double joystickForward) {
        this.joystickForward = joystickForward;
    }

    protected double joystickLateral = 0.0;
    protected double joystickForward = 0.0;

    @Override
    public DriveInstruction getNextInstruction() {
        // TODO Auto-generated method stub
        return new DriveInstruction(joystickForward, joystickLateral);
    }

}
