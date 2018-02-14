package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseDriveSubsystem;

public class CalibratePositionControlCommand extends BaseCommand {

    public static final int CALIBRATION_TIME_MS = 200;
    private BaseDriveSubsystem drive = null;

    public CalibratePositionControlCommand(BaseDriveSubsystem subsystem) {

        super(subsystem, CALIBRATION_TIME_MS);
        this.drive = subsystem;
    }

    @Override
    protected void initialize() {
        drive.startCalibration();
    }

    protected void end() {
        drive.finishCalibration();
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
