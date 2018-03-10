package frc.team281.robot.commands;

    
import frc.team281.robot.subsystems.LifterSubsystem;

public class LifterStopCommand extends BaseCommand {

    private LifterSubsystem lifter;
    public LifterStopCommand(LifterSubsystem subsystem) {
        super(subsystem);
        this.lifter = subsystem;
    }

    @Override
    protected void initialize() {
        lifter.motorsOff();
    }

    @Override
	protected void execute() {
        lifter.motorsOff();
	}

	@Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void interrupted() {
        lifter.motorsOff();
    }
}
