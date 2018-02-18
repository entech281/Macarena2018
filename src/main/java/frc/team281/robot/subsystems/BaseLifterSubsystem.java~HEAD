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
public abstract class BaseLifterSubsystem extends BaseSubsystem {

    public void setHeight(double h) {
        SmartDashboard.putNumber("Height", h);
    } 
    
    public void currentHeight(double c) {
        SmartDashboard.putNumber("Current Height", c);
    }
    
	public void raise() {
	    SmartDashboard.putBoolean("Raise", true);
	}
	
	public void lower() {
	    SmartDashboard.putBoolean("Lower", true);
	}
	
	public void scaleHigh() {
	    SmartDashboard.putBoolean("Scale High", true);
	    }
	
    public void scaleMid() {
        SmartDashboard.putBoolean("Scale Mid", true);
        }
    
    public void scaleLow() {
        SmartDashboard.putBoolean("Scale low", true);
        }

    public void fence() {
        SmartDashboard.putBoolean("Fence", true);
        }
    
    public void ground() {
        SmartDashboard.putBoolean("Ground", true);
        }
    
}
