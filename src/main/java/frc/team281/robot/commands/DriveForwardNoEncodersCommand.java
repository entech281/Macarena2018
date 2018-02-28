package frc.team281.robot.commands;

import frc.team281.robot.DriveInstruction;
import frc.team281.robot.DriveInstructionSource;
import frc.team281.robot.subsystems.drive.BaseDriveSubsystem.DriveMode;
import frc.team281.robot.subsystems.drive.RealDriveSubsystem;

public class DriveForwardNoEncodersCommand extends BaseCommand {

	private RealDriveSubsystem drive;
	private double driveForwardSpeed = 0.0;
	public DriveForwardNoEncodersCommand(RealDriveSubsystem drive, long driveTimeMilliSeconds,double driveFowardSpeed) {
		super(drive,driveTimeMilliSeconds);
		this.drive = drive;
		this.driveForwardSpeed = driveFowardSpeed;
	}

	@Override
	protected void initialize() {
		drive.setMode(DriveMode.SPEED_DRIVE);
		DriveInstructionSource justGoForward = new DriveInstructionSource() {

			@Override
			public DriveInstruction getNextInstruction() {
				return new DriveInstruction( 0.0, driveForwardSpeed);
			}
			
		};
		drive.setupNewDriveSource(justGoForward);
	}

	@Override
	protected void end() {
		drive.restoreOriginalDriveSource();
		drive.stop();
	}

	@Override
	protected boolean isFinished() {		
		return isTimedOut();
	}

}
