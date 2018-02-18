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
		//if ( ! calibrated) {
		//	if ( newDriveMode == DriveMode.SPEED_DRIVE || newDriveMode == DriveMode.POSITION_DRIVE) {
		//		dataLogger.warn("Cannot change mode to "+ newDriveMode + "-->calibration not completed.");
		//		return false;
		//	}
		//}
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
		if (!newController.equals(currentController)) {
			dataLogger.warn("Switching Controllers: " + currentController + "-->" + newController);
			currentController = newController;
			newController.initialize();
		}
		dataLogger.log("CurrentController", currentController.getName());
		newController.periodic();

	}

	public PositionBuffer getPositionBuffer() {
		return positionBuffer;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

}
