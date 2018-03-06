package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;

public class GrabberStopCommand extends BaseCommand {
    private GrabberSubsystem grab;
    public GrabberStopCommand(BaseSubsystem subsystem) {
        super(subsystem);
        grab = (GrabberSubsystem)subsystem;
    }

    public GrabberStopCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
        
    }
    
    @Override
    protected void initialize() {
        grab.stopMotors();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}
