package frc.team281.robot.commands;


import frc.team281.robot.subsystems.LifterSubsystem;

public class LifterLowerSeconds extends BaseCommand {

    private LifterSubsystem lifter;
    public LifterLowerSeconds(LifterSubsystem subsystem,double seconds) {
        super(subsystem);
        this.lifter = subsystem;
        setTimeout(seconds);
    }


    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        lifter.motorsDown(LifterSubsystem.DOWN_SPEED_PERCENT);
    }

    @Override
	protected void end() {
    	    lifter.motorsOff();
	}    

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

	@Override
	protected void interrupted() {
		end();
	}

}
