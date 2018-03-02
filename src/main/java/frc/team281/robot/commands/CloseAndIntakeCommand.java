package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;

public class CloseAndIntakeCommand extends BaseCommand{
    private GrabberSubsystem grab; 
    public CloseAndIntakeCommand(BaseSubsystem subsystem) {
        super(subsystem);
        requires(subsystem);
        this.grab=(GrabberSubsystem) subsystem;
        setTimeout(3000);
    }
    public void inititialize() {
        grab.close();
        grab.startLoading();
    }
    public void end() {
        grab.stopMotors();
    }
    @Override
    protected boolean isFinished() {
        return grab.isCubeTouchingSwitch()||isTimedOut();
    }

}
