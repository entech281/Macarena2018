package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;
public class GrabberOpenCommand extends BaseCommand {
    private GrabberSubsystem grab;
    public GrabberOpenCommand(BaseSubsystem subsystem) {
        super(subsystem);
        grab = (GrabberSubsystem)subsystem;
    }

    public GrabberOpenCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
    }
    @Override
    public void execute() {
        grab.open();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }

}
