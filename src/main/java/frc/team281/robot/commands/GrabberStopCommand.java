package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;

public class GrabberStopCommand extends BaseCommand {
    private GrabberSubsystem grab;
    public GrabberStopCommand(BaseSubsystem subsystem) {
        super(subsystem);
        grab = (GrabberSubsystem)subsystem;
        setTimeout(20);
    }

    public GrabberStopCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
        
    }
    
    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        grab.stopMotors();
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
