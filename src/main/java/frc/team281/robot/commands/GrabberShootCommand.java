package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;

public class GrabberShootCommand extends BaseCommand {

    public GrabberShootCommand(BaseSubsystem subsystem) {
        super(subsystem);
        // TODO Auto-generated constructor stub
    }

    public GrabberShootCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

}
