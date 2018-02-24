package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;

public class GrabberStopCommand extends BaseCommand {
    private GrabberSubsystem grab = new GrabberSubsystem();
    public GrabberStopCommand(BaseSubsystem subsystem) {
        super(subsystem);
        setTimeout(20);
    }

    public GrabberStopCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
        grab.stopMotors();
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
