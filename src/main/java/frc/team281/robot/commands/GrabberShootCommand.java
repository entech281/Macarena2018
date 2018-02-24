package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;

public class GrabberShootCommand extends BaseCommand {
    private GrabberSubsystem grab = new GrabberSubsystem();
    
    public GrabberShootCommand(BaseSubsystem subsystem) {
        super(subsystem);
        setTimeout(2000);
    }

    public GrabberShootCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
    }
    @Override
    public void execute() {
        grab.open();
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
