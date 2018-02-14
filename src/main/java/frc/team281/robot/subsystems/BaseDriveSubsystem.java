package frc.team281.robot.subsystems;

import frc.team281.robot.DriveInstructionSource;
import frc.team281.robot.commands.JoystickDriveCommand;

/**
 * Common base class for the drive subsystem. This contains stuff that's the
 * same for both real operation and for testing. abstract methods should be
 * implemented for real robot use, and for testing.
 * 
 * Note that this class still doesnt use any wpilib stuff!
 * 
 * @author dcowden
 *
 */
public abstract class BaseDriveSubsystem extends BaseSubsystem {

    protected DriveInstructionSource driveInstructionSource;
    protected DriveSystemMode driveMode = new DriveSystemMode();

    public BaseDriveSubsystem(DriveInstructionSource driveInstructionSource) {
        this.driveInstructionSource = driveInstructionSource;
    }

    public abstract void startCalibration();

    public abstract void finishCalibration();

    // these should be implemented using wpilib for the real system,
    // and using fake data for the test system
    public abstract void stop();

    public abstract void arcadeDrive(double forw, double turn);

    public abstract void tankDrive(double left, double right);

    public abstract void drive(Position desiredPosition);

    public abstract Position getCurrentPosition();

    // an example where some functionality is shared for both test and drive
    // implementation
    // note that tests will need to provide a fake driveinstructionsource too!
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickDriveCommand(this, driveInstructionSource));
    }

}
