package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseDriveSubsystem;
import frc.team281.robot.subsystems.Position;

public class DriveToPositionCommand extends BaseCommand {

    private Position desiredPosition;
    private BaseDriveSubsystem driveSubsystem;

    public static final double TOLERANCE = 1.0;

    public DriveToPositionCommand(BaseDriveSubsystem subsystem, Position position) {
        super(subsystem);
        this.desiredPosition = position;
        this.driveSubsystem = subsystem;
    }

    @Override
    protected void initialize() {
        driveSubsystem.drive(desiredPosition);
    }

    @Override
    protected boolean isFinished() {
        Position currentPosition = driveSubsystem.getCurrentPosition();
        dataLogger.log("PositionLeft", currentPosition.getLeftInches());
        dataLogger.log("PositionRight", currentPosition.getRightInches());
        return this.desiredPosition.isCloseTo(currentPosition, TOLERANCE);
    }

}
