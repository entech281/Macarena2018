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
public abstract class BaseGrabberSubsystem extends BaseSubsystem {

	public void load() {
	    SmartDashboard.putBoolean("Load", true);
	}

	public void shoot() {
        SmartDashboard.putBoolean("Shoot", true);	    
	}
	
	public void stop() {
        SmartDashboard.putBoolean("Stop", true);	    
	}

	public void open() {
        SmartDashboard.putBoolean("Open", true);	    
	}

	public void close() {
        SmartDashboard.putBoolean("Close", true);	    
	}

}
