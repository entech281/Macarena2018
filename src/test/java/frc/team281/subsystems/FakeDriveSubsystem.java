package frc.team281.subsystems;

import frc.team281.robot.DriveInstructionSource;
import frc.team281.robot.subsystems.BaseDriveSubsystem;
import frc.team281.robot.subsystems.Position;

public class FakeDriveSubsystem extends BaseDriveSubsystem {

    private double xPosition = 0.0;
    private double yPosition = 0.0;

    public static final double DRIVE_SPEED = 10.0;
    public static final double DISTANCE_PER_CLICK = DRIVE_SPEED * 0.020;

    public FakeDriveSubsystem(DriveInstructionSource driveInstructionSource) {
        super(driveInstructionSource);
    }

    public double getXPosition() {
        return this.xPosition;
    }

    public double getYPosition() {
        return this.yPosition;
    }

    @Override
    public void stop() {

    }

    // this isnt a very good approximation of real driving,
    // but it serves to illustrate the point
    @Override
    public void arcadeDrive(double forw, double turn) {
        xPosition += (DISTANCE_PER_CLICK * forw);
    }

    @Override
    public void tankDrive(double left, double right) {
        xPosition += (DISTANCE_PER_CLICK * left);
    }

    @Override
    public void drive(Position desiredPosition) {
        // TODO Auto-generated method stub

    }

    @Override
    public Position getCurrentPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub

    }

    @Override
    public void startCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void finishCalibration() {
        // TODO Auto-generated method stub

    }

}
