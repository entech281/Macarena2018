package frc.team281.robot.commands;


import frc.team281.robot.subsystems.LifterSubsystem;

public class LifterLowerCommand extends BaseCommand {

    private LifterSubsystem lifter;
    public LifterLowerCommand(LifterSubsystem subsystem) {
        super(subsystem);
        this.lifter = subsystem;
    }


    @Override
    protected void initialize() {
        lifter.motorsUp(100);
    }


    @Override
    protected boolean isFinished() {
        return false;
    }

}
