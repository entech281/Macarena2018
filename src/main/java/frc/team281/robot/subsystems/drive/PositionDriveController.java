package frc.team281.robot.subsystems.drive;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.team281.robot.controllers.FourTalonEncoderChecker;
import frc.team281.robot.controllers.TalonPositionController;
import frc.team281.robot.controllers.TalonPositionControllerGroup;
import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.PositionSource;


/**
 * Drives to positions given by the position buffer.
 * 
 * @author dcowden
 *
 */
public class PositionDriveController extends BaseDriveController {

    //TODO: this should be computed from the talon settings
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
	public void activate() {

		talons.configureAll();
		
		positionControllerGroup = new TalonPositionControllerGroup(
				new TalonPositionController(talons.getFrontLeft(), talons.getFrontLeftSettings()),
				new TalonPositionController(talons.getFrontRight(), talons.getFrontRightSettings()),
				new TalonPositionController(talons.getRearLeft(), talons.getRearLeftSettings()),
				new TalonPositionController(talons.getRearRight(), talons.getRearRightSettings()));

		positionControllerGroup.resetPosition();

	}

	public boolean isFinished() {
		if (this.desiredPosition == null) {
			return true;
		}
		return getCurrentPosition().isCloseTo(desiredPosition, RealDriveSubsystem.POSITION_TOLERANCE_INCHES);
	}

	public Position getCurrentPosition() {
		return positionControllerGroup.getCurrentPosition(encoderConverter);
	}


	public boolean hasCurrentCommand() {
		return this.desiredPosition != null;
	}
	public void setCurrentCommand(Position position) {
		this.desiredPosition = position;
	}
	public Position getCurrentCommand() {
		return this.desiredPosition;
	}
	
	@Override
	public void periodic() {		
		processPositionCommand();
		FourTalonEncoderChecker checker =  new FourTalonEncoderChecker(talons);
		checker.setMotorsWithBrokenEncodersToFollowers();
		
		dataLogger.log("Motor Status:",checker.friendlyStatus());
		displayControllerStatuses();
	}

	protected void processPositionCommand() {
		if ( hasCurrentCommand() ) {
			Position current = getCurrentPosition();
			Position command = getCurrentCommand();
			if ( command.isCloseTo(current, TOLERANCE_INCHES)) {
			    positionSource.next();
				setCurrentCommand(null);
			}			
		}
		else {
			if ( positionSource.hasNextPosition()) {
				Position p = positionSource.getCurrentPosition();
				setCurrentCommand(p);
				int encoderLeft = encoderConverter.toCounts(p.getLeftInches());
				int encoderRight = encoderConverter.toCounts(p.getRightInches());
				positionControllerGroup.setDesiredPosition(encoderLeft, encoderRight, p.isRelative());
			}
			else {
				
			}
		}		
		if ( hasCurrentCommand() ) {
			dataLogger.log("commandPosition", getCurrentCommand());
		}
		else {
			dataLogger.log("commandPosition", "<IDLE>");
		}		
		dataLogger.log("currentPosition", getCurrentPosition());
		dataLogger.log("updateCount", updateCount++);
		
	}
	
	protected void displayControllerStatuses() {
		displayControllerStatus(talons.getFrontLeft(),"FrontLeft");
		displayControllerStatus(talons.getFrontRight(),"FrontRight");
		displayControllerStatus(talons.getRearLeft(),"RearLeft");
		displayControllerStatus(talons.getRearRight(),"RearRight");
	}
	
	protected void displayControllerStatus(WPI_TalonSRX talon, String name) {
        dataLogger.log(name +"_error", talon.getClosedLoopError(0) );
        dataLogger.log(name +"_errorMsg", talon.getLastError() );
        dataLogger.log(name +"_get", talon.get() );
        dataLogger.log(name +"_percent", talon.getMotorOutputPercent() );
        dataLogger.log(name +"_tvolts", talon.getMotorOutputVoltage() );
        dataLogger.log(name +"_current", talon.getOutputCurrent() );
        dataLogger.log(name +"_pos", talon.getSelectedSensorPosition(0) );
        dataLogger.log(name +"_vel", talon.getSelectedSensorVelocity(0) );		
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub		
	}
	
}
