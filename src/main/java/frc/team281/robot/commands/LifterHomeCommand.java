package frc.team281.robot.commands;

import frc.team281.robot.subsystems.LifterSubsystem;

public class LifterHomeCommand extends BaseCommand {

    public static final int TIMEOUT_SECS = 20;
    
    private LifterSubsystem lifter;
    
    public LifterHomeCommand(LifterSubsystem subsystem) {
        super(subsystem,TIMEOUT_SECS);
        this.lifter = (LifterSubsystem)subsystem;
    }

    @Override
    protected void initialize() {
        lifter.motorsDown(LifterSubsystem.HOMING_SPEED_PERCENT);
    }

    @Override
    protected void end() {
        lifter.motorsOff();
    }

    @Override
    protected boolean isFinished() {
        return lifter.isLifterAtBottom() || isTimedOut();
    }
    
    @Override
    protected void interrupted() {
        end();
    }
}
