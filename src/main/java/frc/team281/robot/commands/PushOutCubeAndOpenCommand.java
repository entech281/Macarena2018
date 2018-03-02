package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;

public class PushOutCubeAndOpenCommand extends BaseCommand{
    private GrabberSubsystem grab;
    public PushOutCubeAndOpenCommand(BaseSubsystem subsystem) {
        super(subsystem);
        requires(subsystem);
        grab = (GrabberSubsystem) subsystem;
        setTimeout(3);
    }
    public void initialize() {
        grab.startShooting();
    }
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
    public void end() {
        grab.stopMotors();
        grab.open();
    }
}
