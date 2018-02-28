package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.WristSubsystem;

public class WristPivotDownCommand extends BaseCommand {
    WristSubsystem wrist;
    public WristPivotDownCommand(BaseSubsystem subsystem) {
        super(subsystem);
        this.wrist = (WristSubsystem)subsystem;
        
    }

    public WristPivotDownCommand(BaseSubsystem subsystem, double timeOut) {
        super(subsystem, timeOut);
    }
    public void execute() {
        wrist.pivotDown();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }

}
