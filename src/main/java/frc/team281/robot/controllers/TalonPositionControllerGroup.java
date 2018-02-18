package frc.team281.robot.controllers;

import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.drive.EncoderInchesConverter;

/**
 * Utilities for operating on a group of controllers
 * 
 * @author dcowden
 *
 */
public class TalonPositionControllerGroup {

	private TalonPositionController frontLeft;
	private TalonPositionController frontRight;
	private TalonPositionController rearLeft;
	private TalonPositionController rearRight;

	public TalonPositionControllerGroup(TalonPositionController frontLeft, TalonPositionController frontRight,
			TalonPositionController rearLeft, TalonPositionController rearRight) {
		this.frontLeft = frontLeft;
		this.frontRight = frontRight;
		this.rearLeft = rearLeft;
		this.rearRight = rearRight;
	}

	public void configureAll() {
	    frontLeft.configure();
	    frontRight.configure();
	    rearLeft.configure();
	    rearRight.configure();
	}
	public void resetMode() {
		frontLeft.resetMode();
		frontRight.resetMode();
		rearLeft.resetMode();
		rearRight.resetMode();
	}

	public void resetPosition() {
		frontLeft.resetPosition();
		frontRight.resetPosition();
		rearLeft.resetPosition();
		rearRight.resetPosition();
	}

	public void setDesiredPosition(int leftPosition, int rightPosition, boolean isRelative) {
		if (isRelative) {
			resetPosition();
		}

		frontLeft.setDesiredPosition(leftPosition);
		rearLeft.setDesiredPosition(leftPosition);
		frontRight.setDesiredPosition(rightPosition);
		rearRight.setDesiredPosition(rightPosition);
	}
	public Position getCurrentPosition(EncoderInchesConverter converter) {

		double leftInches = converter.toInches(computeLeftEncoderCounts());
		double rightInches = converter.toInches(computeRightEncoderCounts());

		// use the average
		return new Position(leftInches, rightInches);

	}	

	public int computeLeftEncoderCounts() {
		int total = 0;
		int count = 0;
		Integer pos = frontLeft.getActualPosition();
		if (pos != null && Math.abs(pos) > 0) {
			total += pos;
			count += 1;
		}
		pos = rearLeft.getActualPosition();
		if (pos != null && Math.abs(pos) > 0) {
			total += pos;
			count += 1;
		}
		if ( count == 0){
		    return 0;
		}
		else{
		    return total / count;
		}
		
	}

	public int computeRightEncoderCounts() {
		int total = 0;
		int count = 0;
		Integer pos = frontRight.getActualPosition();
		if (pos != null && pos > 0 ) {
			total += pos;
			count += 1;
		}
		pos = rearRight.getActualPosition();
		if (pos != null && pos > 0 ) {
			total += pos;
			count += 1;
		}
        if ( count == 0){
            return 0;
        }
        else{
            return total / count;
        }
	}
}
