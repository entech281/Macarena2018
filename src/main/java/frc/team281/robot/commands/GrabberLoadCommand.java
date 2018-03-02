package frc.team281.robot.commands;

import frc.team281.robot.subsystems.GrabberSubsystem;

public class GrabberLoadCommand extends BaseCommand {

    public GrabberSubsystem grabber;

    public static final int TIMEOUT_SECS = 20;
    
    public GrabberLoadCommand(GrabberSubsystem subsystem) {
        super(subsystem, TIMEOUT_SECS);
        this.grabber = (GrabberSubsystem)subsystem;
    }
    
    @Override
    public void initialize() {
        grabber.startLoading();
    }
    
    @Override
    public void execute() {
        
    }
    
    @Override
    protected boolean isFinished() {
        return grabber.isCubeTouchingSwitch() || isTimedOut();
    }
    
    @Override
    public void end() {
        // grabber.stopMotors();
    }
    
    @Override
    public void interrupted() {
        end();
    }

}
