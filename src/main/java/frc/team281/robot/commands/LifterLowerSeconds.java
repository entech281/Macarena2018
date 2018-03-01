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
        lifter.motorsUp(100);
    }


    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
