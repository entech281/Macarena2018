package frc.team281.robot.commands;

import frc.team281.robot.subsystems.LifterSubsystem;

public class LifterRaiseSeconds extends BaseCommand {

    private LifterSubsystem lifter;
    public LifterRaiseSeconds(LifterSubsystem subsystem, double seconds) {
        super(subsystem);
        setTimeout(seconds);
        this.lifter = subsystem;
    }

    @Override
    protected void initialize() {
        lifter.motorsDown(100);
    }

    @Override
	protected void end() {
    	lifter.motorsOff();
	}

	@Override
    protected boolean isFinished() {
        
        return isTimedOut();
    }

}
