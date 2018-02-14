package frc.team281.robot.commands;

import frc.team281.robot.DriveInstruction;
import frc.team281.robot.DriveInstructionSource;
import frc.team281.robot.subsystems.BaseDriveSubsystem;

public class JoystickDriveCommand extends BaseCommand {

    private BaseDriveSubsystem drive;
    private DriveInstructionSource driveInstructionSource;

    public JoystickDriveCommand(BaseDriveSubsystem drive, DriveInstructionSource driveInstructionSource) {
        super(drive, UNLIMITED_TIMEOUT);
        this.drive = drive;
        this.driveInstructionSource = driveInstructionSource;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        DriveInstruction di = driveInstructionSource.getNextInstruction();
        drive.arcadeDrive(di.getForward(), di.getLateral());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
    }

    @Override
    public void interrupted() {
    }
}
