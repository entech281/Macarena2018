package frc.team281.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Common base class for the lifter subsystem. This contains stuff that's the
 * same for both real operation and for testing. abstract methods should be
 * implemented for real robot use, and for testing.
 * 
 * Note that this class still doesnt use any wpilib stuff!
 * 
 * @author dcowden
 */
public abstract class BaseWristSubsystem extends BaseSubsystem {

	public void pivotUp() {
        SmartDashboard.putBoolean("Pivot Up", true);	    
	}

	public void pivotDown() {
        SmartDashboard.putBoolean("Pivot Down", true);	    
	}

}
