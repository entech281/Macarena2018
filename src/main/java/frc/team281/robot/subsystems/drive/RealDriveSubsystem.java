package frc.team281.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team281.robot.DriveInstructionSource;
import frc.team281.robot.RobotMap;
import frc.team281.robot.subsystems.NavXIntializer;
import frc.team281.robot.subsystems.TalonSettings;
import frc.team281.robot.subsystems.TalonSettingsBuilder;

/**
 * This is the drive system that will run in the robot. All the wpilib stuff
 * goes here.
 *
 * ref https://www.chiefdelphi.com/forums/showthread.php?p=1633629 motionMagic
 * is basically a trade name for a trapezoidal motion profile a java example :
 * https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/tree/master/Java/MotionMagic/src/org/usfirst/frc/team217/robot
 * ctre api doc
 * http://www.ctr-electronics.com/downloads/api/java/html/index.html software
 * manual
 * https://github.com/CrossTheRoadElec/Phoenix-Documentation/raw/master/Talon%20SRX%20Victor%20SPX%20-%20Software%20Reference%20Manual.pdf
 *
 * @author dcowden
 *
 */
public class RealDriveSubsystem extends BaseDriveSubsystem {

	public static final int NAVX_CALIBRATION_LOOP_TIME_MS = 50;
	public static final double ENCODER_TICKS_PER_INCH = 43.0;

	//protected FourTalonGroup talons;
	private AHRS navX;

	//private FourDriveTalonCalibratorController calibrator;
	private BasicArcadeDriveController arcadeDrive;
	private PositionDriveController positionDrive;
	private DriveInstructionSource driveInstructionSource;
	
	private FourTalonsWithSettings speedModeTalons;
	private FourTalonsWithSettings positionModeTalons;
	
	private WPI_TalonSRX frontLeftMotor;
	private WPI_TalonSRX frontRightMotor;
	private WPI_TalonSRX rearLeftMotor;
	private WPI_TalonSRX rearRightMotor;
	
	public RealDriveSubsystem(DriveInstructionSource driveInstructionSource) {
		this.driveInstructionSource = driveInstructionSource;
	}

	@Override
	public void initialize() {

		this.navX = new NavXIntializer(SerialPort.Port.kMXP,NAVX_CALIBRATION_LOOP_TIME_MS).getCalibratedNavX();	
		

		frontLeftMotor = new WPI_TalonSRX(RobotMap.CAN.FRONT_LEFT_MOTOR);
		frontRightMotor = new WPI_TalonSRX(RobotMap.CAN.FRONT_RIGHT_MOTOR);
		rearLeftMotor = new WPI_TalonSRX(RobotMap.CAN.REAR_LEFT_MOTOR);
		rearRightMotor = new WPI_TalonSRX(RobotMap.CAN.REAR_RIGHT_MOTOR);
		
		TalonSettings leftSpeedSettings = TalonSettingsBuilder.defaults()
				.withCurrentLimits(35, 30, 200)
				.coastInNeutral()
				.withDirections(false, false)
				.limitMotorOutputs(1.0, 0.1)
				.noMotorStartupRamping()
				.useSpeedControl()
				.build();
		TalonSettings rightSpeedSettings = TalonSettingsBuilder.inverted(leftSpeedSettings);
		

		speedModeTalons = new FourTalonsWithSettings(
		        frontLeftMotor,
		        rearLeftMotor,
		        frontRightMotor, 		        
		        rearRightMotor,
		        leftSpeedSettings,
		        rightSpeedSettings);		

		TalonSettings leftPositionSettings = TalonSettingsBuilder.defaults()
				.withCurrentLimits(35, 30, 200)
				.coastInNeutral()
				.withDirections(false, false)
				.noMotorOutputLimits()
				.noMotorStartupRamping()
				.usePositionControl()
				.withGains(0.3,0.5, 0.0, 0.0)
				.withMotionProfile(500, 600)
				.build();

		TalonSettings rightPositionSettings = TalonSettingsBuilder.inverted(leftPositionSettings);

		
		positionModeTalons = new FourTalonsWithSettings(
                frontLeftMotor,
                rearLeftMotor,                
                frontRightMotor, 
                rearRightMotor,
                leftPositionSettings,
                rightPositionSettings);
				
		arcadeDrive = new BasicArcadeDriveController(speedModeTalons, driveInstructionSource);
		positionDrive = new PositionDriveController(positionModeTalons, getPositionBuffer(), 
				        new EncoderInchesConverter(ENCODER_TICKS_PER_INCH));	
		
	}

	@Override
	public void periodic() {
		dataLogger.log("DriveMode", driveMode + "");
		
		dataLogger.log("frontLeftEncoder", frontLeftMotor.getSelectedSensorPosition(0));
		dataLogger.log("frontRightEncoder", frontRightMotor.getSelectedSensorPosition(0));
		dataLogger.log("rearLeftEncoder", rearLeftMotor.getSelectedSensorPosition(0));
		dataLogger.log("rearRightEncoder", rearRightMotor.getSelectedSensorPosition(0));		
		
		if (driveMode == DriveMode.POSITION_DRIVE) {
			runController(positionDrive);
		} else if (driveMode == DriveMode.SPEED_DRIVE) {
			runController(arcadeDrive);
		} else {
			// do nothing
		}
	}

}
