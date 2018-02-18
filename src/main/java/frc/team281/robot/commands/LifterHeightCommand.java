package frc.team281.robot.commands;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.LifterSubsystem;


public class LifterHeightCommand extends BaseCommand {

    public static final int TIMEOUT_SECS = 20;
    public static final double HEIGHT_TOLERANCE=1.0;
    
    private LifterSubsystem lifter;
    
    private double desiredHeightInches = 0.0;
    public LifterHeightCommand(BaseSubsystem subsystem, double heightInches) {
        super(subsystem, TIMEOUT_SECS);
        this.lifter = (LifterSubsystem)subsystem;
       
        this.desiredHeightInches = lifter.trimHeight(heightInches);
    }
    
    @Override
    protected void initialize() {
        double currentHeightInches = lifter.getHeightInches();
        if ( currentHeightInches < desiredHeightInches){            
            lifter.motorsUp(LifterSubsystem.FULL_SPEED_PERCENT);
        }
        else{
            lifter.motorsDown(LifterSubsystem.FULL_SPEED_PERCENT);
        }        
    }

    @Override
    protected void end() {
        lifter.motorsOff();
    }

    @Override
    protected boolean isFinished() {
        boolean isAtRequestedHeight = isHeightAtRequestedHeight();
        dataLogger.log("At Reqeusted Height",isAtRequestedHeight);
        return isAtRequestedHeight || isTimedOut();
    }
    
    protected boolean isHeightAtRequestedHeight(){
        double currentHeightInches= lifter.getHeightInches();
        dataLogger.log("RequestedHeight",desiredHeightInches);
        dataLogger.log("CurrentHeight",currentHeightInches);
        return Math.abs(currentHeightInches - desiredHeightInches) < HEIGHT_TOLERANCE;
    }
    
    @Override
    protected void interrupted() {
        end();
    }

}
