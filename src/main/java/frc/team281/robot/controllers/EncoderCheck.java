package frc.team281.robot.controllers;

import edu.wpi.first.wpilibj.DriverStation;
import frc.team281.robot.RobotMap;
import frc.team281.robot.subsystems.TalonSettingsBuilder;
import frc.team281.robot.subsystems.drive.FourTalonsWithSettings;

/**
 * Checks to see how we should configure motors, based on encoder readings
 * 
 * @author dcowden
 *
 */
public class EncoderCheck {

	private int leftRearCounts = 0;
	private int rightRearCounts = 0;
	private int leftFrontCounts = 0;
	private int rightFrontCounts = 0;

	public EncoderCheck(int leftRearCounts, int leftFrontCounts, int rightFrontCounts, int rightRearCounts) {
		this.leftFrontCounts = leftFrontCounts;
		this.rightFrontCounts = rightFrontCounts;
		this.rightRearCounts = rightRearCounts;
		this.leftRearCounts = leftRearCounts;
	}
	
	public EncoderCheck(FourTalonsWithSettings talons ) {
		this(   talons.getRearLeft().getSelectedSensorPosition(0),
				talons.getFrontLeft().getSelectedSensorPosition(0),
				talons.getFrontRight().getSelectedSensorPosition(0),
				talons.getRearRight().getSelectedSensorPosition(0) );
	}
	
	public void adjustTalonSettingsToWorkAroundBrokenEncoders(FourTalonsWithSettings originalTalons) {
		if (shouldDisableAll()) {
			originalTalons.disableAllSettings();
		} else {
			if (shouldLeftFrontFollowLeftRear()) {
				originalTalons.setFrontLeftSettings(
						TalonSettingsBuilder.follow(originalTalons.getRearLeftSettings(), RobotMap.CAN.FRONT_RIGHT_MOTOR));
			}
			if (shouldLeftRearFollowLeftFront()) {
				originalTalons.setRearLeftSettings(
						TalonSettingsBuilder.follow(originalTalons.getFrontLeftSettings(), RobotMap.CAN.FRONT_LEFT_MOTOR));
			}
			if (shouldRightFrontFollowRightRear()) {
				originalTalons.setFrontRightSettings(
						TalonSettingsBuilder.follow(originalTalons.getRearRightSettings(), RobotMap.CAN.FRONT_RIGHT_MOTOR));
			}
			if (shouldRightRearFollowRightFront()) {
				originalTalons.setRearRightSettings(
						TalonSettingsBuilder.follow(originalTalons.getFrontRightSettings(), RobotMap.CAN.FRONT_RIGHT_MOTOR));

			}
		}		
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

	public boolean isLeftRearOk() {
		return this.leftRearCounts > 0;
	}

	public boolean isLeftFrontOk() {
		return this.leftFrontCounts > 0;
	}

	public boolean isRightRearOk() {
		return this.rightRearCounts > 0;
	}

	public boolean isRightFrontOk() {
		return this.rightFrontCounts > 0;
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
