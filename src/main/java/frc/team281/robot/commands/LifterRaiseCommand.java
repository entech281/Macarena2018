package frc.team281.robot.commands;

import frc.team281.robot.subsystems.LifterSubsystem;

public class LifterRaiseCommand extends BaseCommand {

    private LifterSubsystem lifter;
    public LifterRaiseCommand(LifterSubsystem subsystem) {
        super(subsystem);
        this.lifter = subsystem;
    }

    @Override
    protected void initialize() {
        lifter.motorsDown(100);
    }

    @Override
    protected boolean isFinished() {
        
        return isTimedOut();
    }

}
