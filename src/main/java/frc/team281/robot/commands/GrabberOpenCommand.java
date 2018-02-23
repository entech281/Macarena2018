package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;
public class GrabberOpenCommand extends BaseCommand {
    private GrabberSubsystem _grab = new GrabberSubsystem();
    public GrabberOpenCommand(BaseSubsystem subsystem) {
        super(subsystem);
        setTimeout(2000);
    }

    public GrabberOpenCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
    }
    @Override
    public void execute() {
        _grab.open();
    }
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
