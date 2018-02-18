package frc.team281.robot.commands;

import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.drive.BaseDriveSubsystem;

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
        driveSubsystem.getPositionBuffer().addPosition(desiredPosition);
    }

    @Override
    protected boolean isFinished() {
        return ! driveSubsystem.getPositionBuffer().hasNextPosition();
    }

}
