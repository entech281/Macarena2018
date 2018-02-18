package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;

public class GrabberCloseCommand extends BaseCommand {

    public GrabberCloseCommand(BaseSubsystem subsystem) {
        super(subsystem);
        // TODO Auto-generated constructor stub
    }

    public GrabberCloseCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

}
