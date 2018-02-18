package frc.team281.robot.subsystems.drive;

import frc.team281.robot.controllers.TalonPositionController;
import frc.team281.robot.controllers.TalonPositionControllerGroup;
import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.PositionSource;

public class PositionDriveController extends BaseDriveController {

	public static final double TOLERANCE_INCHES = 1.0;

	private FourTalonsWithSettings talons;
	private TalonPositionControllerGroup positionControllerGroup;
	private EncoderInchesConverter encoderConverter;
	private Position desiredPosition;
	private PositionSource positionSource;
	private int updateCount = 0;
	
	public PositionDriveController(FourTalonsWithSettings talons, PositionSource positionSource,
			EncoderInchesConverter encoderConverter) {
		this.talons = talons;
		this.encoderConverter = encoderConverter;
		this.positionSource = positionSource;
	}

	@Override
	public void initialize() {

		talons.configureAll();
		
		positionControllerGroup = new TalonPositionControllerGroup(
				new TalonPositionController(talons.getFrontLeft(), talons.getFrontLeftSettings()),
				new TalonPositionController(talons.getFrontRight(), talons.getFrontRightSettings()),
				new TalonPositionController(talons.getRearLeft(), talons.getRearLeftSettings()),
				new TalonPositionController(talons.getRearRight(), talons.getRearRightSettings()));

		positionControllerGroup.resetPosition();
		dataLogger.log("Initialized","true");
	}

	public boolean isFinished() {
		if (this.desiredPosition == null) {
			return true;
		}
		return getCurrentPosition().isCloseTo(desiredPosition, TOLERANCE_INCHES);
	}

	public Position getCurrentPosition() {

		int leftEncoderCount = positionControllerGroup.computeLeftEncoderCounts();
		int rightEncoderCount = positionControllerGroup.computeRightEncoderCounts();

		double leftInches = encoderConverter.toInches(leftEncoderCount);
		double rightInches = encoderConverter.toInches(rightEncoderCount);

		// use the average
		return new Position(leftInches, rightInches);

	}

	@Override
	public void periodic() {
	    dataLogger.warn(" Top Loop");
		dataLogger.log("isFinished", isFinished());
		dataLogger.log("desiredPosition", desiredPosition);
		dataLogger.log("currentPosition", getCurrentPosition());
		dataLogger.log("updateCount", updateCount++);
		if (isFinished()) {
			Position p = positionSource.getNextPosition();
			dataLogger.log("nextPositon", p + "");
			if (p != null) {
				this.desiredPosition = p;
				dataLogger.warn("Updated Position ok, desired=" + this.desiredPosition);
				int encoderLeft = encoderConverter.toCounts(p.getLeftInches());
				int encoderRight = encoderConverter.toCounts(p.getRightInches());
				positionControllerGroup.setDesiredPosition(encoderLeft, encoderRight, p.isRelative());
				dataLogger.warn("Set Position ok");
			} else {
				dataLogger.warn("Next Position is Null. No additional Command Requested.");
			}

		}
		dataLogger.warn("Got To Bottom of Loop");
		dataLogger.log("frontLeftMode", talons.getFrontLeft().getControlMode() );
		dataLogger.log("rearLeftMode", talons.getRearLeft().getControlMode() );
        dataLogger.log("leftError", talons.getFrontLeft().getClosedLoopError(0) );
        dataLogger.log("leftErroMsg", talons.getFrontLeft().getLastError() );
        dataLogger.log("leftget", talons.getFrontLeft().get() );
        dataLogger.log("leftpercent", talons.getFrontLeft().getMotorOutputPercent() );
        dataLogger.log("leftvolts", talons.getFrontLeft().getMotorOutputVoltage() );
        dataLogger.log("leftcurrent", talons.getFrontLeft().getOutputCurrent() );
        dataLogger.log("leftpos", talons.getFrontLeft().getSelectedSensorPosition(0) );
        dataLogger.log("leftvel", talons.getFrontLeft().getSelectedSensorVelocity(0) );
	}

}
