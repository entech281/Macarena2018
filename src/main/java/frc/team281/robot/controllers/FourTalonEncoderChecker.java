package frc.team281.robot.controllers;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.team281.robot.RobotMap;
import frc.team281.robot.logger.DataLogger;
import frc.team281.robot.logger.DataLoggerFactory;
import frc.team281.robot.subsystems.drive.FourTalonsWithSettings;

public class FourTalonEncoderChecker {

	
	//if any single encoder on a side is this value, 
	//and the other is not, then we'll consider an encoder broken
	public static final int MIN_COUNTS_TO_CONSIDER=20;
	
	private DataLogger log;
	private FourTalonsWithSettings talons;
	
	public FourTalonEncoderChecker(FourTalonsWithSettings talons) {
		this.talons = talons;
		this.log = DataLoggerFactory.getLoggerFactory().createDataLogger(getClass().getSimpleName());
	}
	
	//TODO: needs unit test	
	public String friendlyStatus(){
	    StringBuffer sb = new StringBuffer();
	    if ( allOk() ){
	        sb.append("OK::");
	    }
	    else {
	        if ( shouldDisableAll() ){
	            sb.append("CANT DRIVE:: ");
	        }
	        else{
	            sb.append("IMPAIRED:: ");
	        }
	    }
	    sb.append(String.format("LF: %s LR: %s RF: %s RR: %s", 
	            convertBooleanToMotorStatus(shouldLeftFrontFollowLeftRear()),
	            convertBooleanToMotorStatus(shouldLeftRearFollowLeftFront()),
	            convertBooleanToMotorStatus(shouldRightFrontFollowRightRear()),
	            convertBooleanToMotorStatus(shouldRightRearFollowRightFront())
	      ));

	    
	    return sb.toString();
	}
	
	protected String convertBooleanToMotorStatus(boolean status){
	    if ( status ){
	        return "[-]";
	    }
	    else{
	        return "[+]";
	    }
	}
	public void setMotorsWithBrokenEncodersToFollowers( ) {
		
		//set these directly on the talons for immediate results.
		//another way would be to change the settings and then apply them to the talons,
		//but that would make the follower mode persistent, which i dont think we want
		
		if (shouldDisableAll()) {
			talons.getFrontLeft().set(ControlMode.Disabled, 0);
			talons.getFrontRight().set(ControlMode.Disabled, 0);
			talons.getRearLeft().set(ControlMode.Disabled, 0);
			talons.getRearRight().set(ControlMode.Disabled, 0);
		} else {
			if (shouldLeftFrontFollowLeftRear()) {
				log.warn("Left Front Encoder appears to be broken. It will follow Left Rear");
				talons.getFrontLeft().set(ControlMode.Follower, RobotMap.CAN.REAR_LEFT_MOTOR);
			}
			if (shouldLeftRearFollowLeftFront()) {
				log.warn("Left Rear Encoder appears to be broken. It will follow Left Front");
				talons.getRearLeft().set(ControlMode.Follower,RobotMap.CAN.FRONT_LEFT_MOTOR);
			}
			if (shouldRightFrontFollowRightRear()) {
				log.warn("Right Front Encoder appears to be broken. It will follow Right Rear");
				talons.getFrontRight().set(ControlMode.Follower,RobotMap.CAN.REAR_RIGHT_MOTOR);
			}
			if (shouldRightRearFollowRightFront()) {
				log.warn("Right Rear Encoder appears to be broken. It will follow Right Front");
				talons.getRearRight().set(ControlMode.Follower,RobotMap.CAN.FRONT_RIGHT_MOTOR );
			}
		}		
		log.log("frontLeftMode",talons.getFrontLeft().getControlMode());
		log.log("frontRightMode",talons.getFrontRight().getControlMode());
		log.log("rearLeftMode",talons.getRearLeft().getControlMode());
		log.log("rearRightMode",talons.getRearRight().getControlMode());
		
	}
	
	public boolean shouldLeftFrontFollowLeftRear() {
		return hasProblems() && canDrive() && isLeftRearOk() && (!isLeftFrontOk());
	}

	public boolean shouldLeftRearFollowLeftFront() {
		return hasProblems() && canDrive() && isLeftFrontOk() && (!isLeftRearOk());
	}

	public boolean shouldRightRearFollowRightFront() {
		return hasProblems() && canDrive() && isRightFrontOk() && (!isRightRearOk());
	}

	public boolean shouldRightFrontFollowRightRear() {
		return hasProblems() && canDrive() && isRightRearOk() && (!isRightFrontOk());
	}

	public boolean shouldDisableAll() {
		return this.hasProblems() && !this.canDrive();
	}

	protected boolean checkPair(WPI_TalonSRX primary, WPI_TalonSRX secondary) {
		int myCounts = primary.getSelectedSensorPosition(0);
		int myPairCounts = secondary.getSelectedSensorPosition(0);
		if ( Math.abs(myPairCounts) > MIN_COUNTS_TO_CONSIDER && myCounts == 0) {
			return false;
		}
		else {
			return true;
		}		
	}
	public boolean isLeftRearOk() {
		return checkPair(talons.getRearLeft(),talons.getFrontLeft());
	}

	public boolean isLeftFrontOk() {
		return checkPair(talons.getFrontLeft(),talons.getRearLeft());
	}

	public boolean isRightRearOk() {
		return checkPair(talons.getRearRight(),talons.getFrontRight());
	}

	public boolean isRightFrontOk() {
		return checkPair(talons.getFrontRight(),talons.getRearRight());
	}

	public boolean isLeftOk() {
		return isLeftFrontOk() || isLeftRearOk();
	}

	public boolean isRightOk() {
		return isRightFrontOk() || isRightRearOk();
	}

	public boolean canDrive() {
		return isLeftOk() && isRightOk();
	}

	public boolean hasLeftProblems() {
		return !isLeftOk();
	}

	public boolean hasRightProblems() {
		return !isRightOk();
	}

	public boolean hasProblems() {
		return !allOk();
	}

	public boolean allOk() {
		return isLeftRearOk() && isLeftFrontOk() && isRightRearOk() && isRightFrontOk();
	}	
	
	
}
