package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.WristSubsystem;

public class WristPivotUpCommand extends BaseCommand {
    WristSubsystem wrist=new WristSubsystem();
    public WristPivotUpCommand(BaseSubsystem subsystem) {
        super(subsystem);
    }

    public WristPivotUpCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
        wrist.pivotUp();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}
