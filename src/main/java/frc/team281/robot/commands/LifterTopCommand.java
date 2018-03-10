package frc.team281.robot.commands;

import frc.team281.robot.subsystems.LifterSubsystem;

public class LifterTopCommand extends BaseCommand {

	public static final int TIMEOUT_SECS = 5;

	private LifterSubsystem lifter;

	public LifterTopCommand(LifterSubsystem subsystem) {
		super(subsystem, TIMEOUT_SECS);
		this.lifter = (LifterSubsystem) subsystem;
	}

	@Override
	protected void initialize() {
		lifter.motorsUp(LifterSubsystem.UP_SPEED_PERCENT);
	}

	@Override
	protected void end() {
		lifter.motorsOff();
	}

	@Override
	protected boolean isFinished() {
		return lifter.isLifterAtTop() || isTimedOut();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
