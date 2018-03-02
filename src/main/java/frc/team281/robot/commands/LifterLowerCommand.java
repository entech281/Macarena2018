package frc.team281.robot.commands;


import frc.team281.robot.subsystems.LifterSubsystem;

public class LifterLowerCommand extends BaseCommand {

    private LifterSubsystem lifter;
    public LifterLowerCommand(LifterSubsystem subsystem) {
        super(subsystem);
        this.lifter = subsystem;
    }


    @Override
    protected void initialize() {
        lifter.motorsUp(LifterSubsystem.DOWN_SPEED_PERCENT);
    }

	@Override
	protected void end() {
		lifter.motorsOff();
	}

    @Override
    protected boolean isFinished() {
        return true;
    }

	@Override
	protected void interrupted() {
		end();
	}

}
