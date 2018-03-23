package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;

public class GrabberShootCommand extends BaseCommand {
    private GrabberSubsystem grab;
    
    public GrabberShootCommand(BaseSubsystem subsystem) {
        super(subsystem);
        grab = (GrabberSubsystem)subsystem;
    }

    public GrabberShootCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
        grab = (GrabberSubsystem)subsystem;
    }
    @Override
    public void execute() {
        grab.startShooting();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
    @Override
    public void end() {
        grab.stopMotors();
    }
    
    @Override
    public void interrupted() {
        
    }
}
