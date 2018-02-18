package frc.team281.robot.subsystems.drive;

import frc.team281.robot.subsystems.BaseSubsystem;
import frc.team281.robot.subsystems.PositionBuffer;

/**
 * Common base class for the drive subsystem. This contains stuff that's the
 * same for both real operation and for testing. abstract methods should be
 * implemented for real robot use, and for testing.
 * 
 * Note that this class still doesnt use any wpilib stuff!
 * 
 * 
 * @author dcowden
 *
 */
public abstract class BaseDriveSubsystem extends BaseSubsystem {

	public enum DriveMode {
		DISABLED, CALIBRATE, READY, SPEED_DRIVE, POSITION_DRIVE
	}

	protected DriveMode driveMode = DriveMode.DISABLED;
	protected BaseDriveController currentController;
	protected PositionBuffer positionBuffer = new PositionBuffer();
	protected boolean calibrated = false;
	
	
	/**
	 * Changes modes, and returns true if the change worked, false if it didnt
	 * @param newDriveMode
	 * @return
	 */
	public boolean setMode ( DriveMode newDriveMode) {
		if ( newDriveMode == driveMode) {
			dataLogger.warn("Stayed in mode " + newDriveMode );
			return false;
		}
		
		driveMode = newDriveMode;
		dataLogger.warn("Drive Mode Changed:" + driveMode + "-->" + newDriveMode);
		return true;
		
	}
	
	
	
	public void runController(BaseDriveController newController) {
	    if ( newController == null ){
	        dataLogger.warn("Trying to run null controller!");
	    }
	    //TODO: clean up a lot of this mess by having a NullController rather than null reference
		if (!newController.equals(currentController)) {
		    String newControllerName = "<none>";
		    
			dataLogger.warn("Switching Controllers: " + 
			        computeControllerName(currentController) + "-->" + computeControllerName(newController));
			if ( currentController != null ){
			    currentController.deactivate();
			}						
			newController.activate();
			currentController = newController;
		}
		
		dataLogger.log("CurrentController", currentController.getName());
		newController.periodic();

	}

	protected static String computeControllerName(BaseDriveController controller){
	    if ( controller == null ){
	        return "<none>";
	    }
	    else{
	        return controller.getName();
	    }
	}
	public PositionBuffer getPositionBuffer() {
		return positionBuffer;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

}
