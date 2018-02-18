package frc.team281.robot.subsystems.drive;

import frc.team281.robot.controllers.EncoderCheck;
import frc.team281.robot.controllers.TalonSpeedController;

/**
 * Moves four motors ahead, just enough to test that the encoders work.
 * 
 * @author dcowden
 *
 */
public class MotorEncoderTester {

	protected TalonSpeedController leftFrontMotor;
	protected TalonSpeedController leftRearMotor;
	protected TalonSpeedController rightFrontMotor;
	protected TalonSpeedController rightRearMotor;
	private double testSpeed = 0.1;
	private boolean running = false;
	
	public boolean isRunning() {
		return running;
	}

	public MotorEncoderTester(TalonSpeedController leftFrontMotor, TalonSpeedController leftRearMotor,
			TalonSpeedController rightFrontMotor, TalonSpeedController rightRearMotor, double testSpeed) {

		this.leftFrontMotor = leftFrontMotor;
		this.leftRearMotor = leftRearMotor;
		this.rightFrontMotor = rightFrontMotor;
		this.rightRearMotor = rightRearMotor;
		this.testSpeed = testSpeed;
	}

	public void startTest() {
		leftFrontMotor.resetPosition();
		leftRearMotor.resetPosition();
		rightFrontMotor.resetPosition();
		rightRearMotor.resetPosition();

		leftFrontMotor.setDesiredSpeed(testSpeed);
		leftRearMotor.setDesiredSpeed(testSpeed);
		rightFrontMotor.setDesiredSpeed(testSpeed);
		rightRearMotor.setDesiredSpeed(testSpeed);
		this.running = true;
	}

	public EncoderCheck finishTest() {

		leftFrontMotor.setDesiredSpeed(0);
		leftRearMotor.setDesiredSpeed(0);
		rightFrontMotor.setDesiredSpeed(0);
		rightRearMotor.setDesiredSpeed(0);
		this.running = false;
		return new EncoderCheck(leftRearMotor.getActualPosition(), leftFrontMotor.getActualPosition(),
				rightFrontMotor.getActualPosition(), rightRearMotor.getActualPosition());

	}

}
