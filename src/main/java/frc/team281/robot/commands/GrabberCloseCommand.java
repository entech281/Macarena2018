package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;
public class GrabberCloseCommand extends BaseCommand {
    private GrabberSubsystem grab;
    public GrabberCloseCommand(BaseSubsystem subsystem) {
        super(subsystem);
        grab = (GrabberSubsystem)subsystem;
        setTimeout(2);
    }

    public GrabberCloseCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
    }

    @Override
    protected void initialize() {
        grab.close();
    }

    @Override
    public void execute() {
        grab.close();
    }

    @Override
    protected void end() {
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void interrupted() {
        end();
    }

}
