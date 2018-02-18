package frc.team281.robot.subsystems.drive;

import frc.team281.robot.RobotMap;
import frc.team281.robot.controllers.EncoderCheck;
import frc.team281.robot.controllers.TalonSpeedController;
import frc.team281.robot.subsystems.TalonSettingsBuilder;

/**
 * Given four CAN Talons and some desired settings, gives a controller group
 * that will control the position, even if some of the encoders are broken
 * 
 * @author dcowden
 *
 */
public class FourDriveTalonCalibratorController extends BaseDriveController {

	private MotorEncoderTester tester;
	private long calibrationTimeMs = 0;
	private long startTimeMillis = 0;
	private FourTalonsWithSettings talons;	
	private EncoderCheck checker;
	
	public FourDriveTalonCalibratorController(FourTalonsWithSettings talons, long calibrationTimeMs) {
		this.talons = talons;
		this.calibrationTimeMs = calibrationTimeMs;
	}

	@Override
	public void initialize() {
		
		talons.configureAll();
		
		tester = new MotorEncoderTester(new TalonSpeedController(talons.getFrontLeft(), talons.getFrontLeftSettings()),
				new TalonSpeedController(talons.getRearLeft(), talons.getRearLeftSettings()),
				new TalonSpeedController(talons.getFrontRight(), talons.getFrontRightSettings()),
				new TalonSpeedController(talons.getRearRight(), talons.getRearRightSettings()), calibrationTimeMs);

		tester.startTest();
		
		startTimeMillis = System.currentTimeMillis();

	}

	public void adjustTalonSettingsToWorkAroundBrokenEncoders(FourTalonsWithSettings originalTalons) {

		if ( checker == null ) {
			dataLogger.warn("Attempt to get calibration result when calibration is not finished.");
			return;
		}
		
		dataLogger.log("Calibration Finished", true);
		dataLogger.log("LeftFrontMotorGood", checker.isLeftFrontOk());
		dataLogger.log("LeftRearMotorGood", checker.isLeftRearOk());
		dataLogger.log("RightFrontMotorGood", checker.isRightFrontOk());
		dataLogger.log("RightRearMotorGood", checker.isRightRearOk());
		checker.adjustTalonSettingsToWorkAroundBrokenEncoders(originalTalons);
		
	}

	public boolean isCalibrationReady() {
		if ( tester == null ) {
			return false;
		}
		else if ( tester.isRunning() ) {
			long currentTime = System.currentTimeMillis();
			return currentTime > (startTimeMillis + this.calibrationTimeMs);			
		}
		else {
			return false;
		}

	}

	@Override
	public void periodic() {
		boolean calibrated = isCalibrationReady();
		dataLogger.log("CalibrationComplete", calibrated);
		if ( calibrated && checker == null ) {
			checker = tester.finishTest();
		}

	}

}
