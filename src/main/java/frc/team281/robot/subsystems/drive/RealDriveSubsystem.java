package frc.team281.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import frc.team281.robot.DriveInstructionSource;
import frc.team281.robot.RobotMap;
import frc.team281.robot.subsystems.TalonSettings;
import frc.team281.robot.subsystems.TalonSettingsBuilder;
import frc.team281.robot.subsystems.NavXIntializer;

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
	public static final double ENCODER_TICKS_PER_INCH = 44.88;

	public static final int POSITION_ENCODER_TOLERANCE = 25;
	public static final double POSITION_TOLERANCE_INCHES = (double)POSITION_ENCODER_TOLERANCE/ ENCODER_TICKS_PER_INCH;
	
	//protected FourTalonGroup talons;
	private AHRS navX = null;
    private boolean collisionDetected;
    private double lastWorldAccelX = 0.0;
    private double lastWorldAccelY = 0.0;
    private static final double COLLISION_THRESHOLD_DELTA_G = 0.5;
    private static final double TIP_THRESHOLD_DEGREES = 15.0;

	//private FourDriveTalonCalibratorController calibrator;
	private BasicArcadeDriveController arcadeDrive;
	private PositionDriveController positionDrive;
	protected DoNothingDriveController doNothing = new DoNothingDriveController();
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
        this.collisionDetected = false;
        this.lastWorldAccelX = 0.0;
        this.lastWorldAccelY = 0.0;

		frontLeftMotor = new WPI_TalonSRX(RobotMap.CAN.FRONT_LEFT_MOTOR);
		frontRightMotor = new WPI_TalonSRX(RobotMap.CAN.FRONT_RIGHT_MOTOR);
		rearLeftMotor = new WPI_TalonSRX(RobotMap.CAN.REAR_LEFT_MOTOR);
		rearRightMotor = new WPI_TalonSRX(RobotMap.CAN.REAR_RIGHT_MOTOR);
		
		TalonSettings leftFrontSpeedSettings = TalonSettingsBuilder.defaults()
				.withCurrentLimits(35, 30, 200)
				.coastInNeutral()
				.withDirections(false, false)
				.limitMotorOutputs(1.0, 0.2)
				.noMotorStartupRamping()
				.useSpeedControl()
				.build();
		TalonSettings rightFrontSpeedSettings = TalonSettingsBuilder.defaults()
                .withCurrentLimits(35, 30, 200)
                .coastInNeutral()
                .withDirections(false, true)
                .limitMotorOutputs(1.0, 0.2)
                .noMotorStartupRamping()
                .useSpeedControl()
                .build();
		TalonSettings leftRearSpeedSettings = TalonSettingsBuilder.defaults()
                .withCurrentLimits(35, 30, 200)
                .coastInNeutral()
                .withDirections(false, false)
                .limitMotorOutputs(1.0, 0.2)
                .noMotorStartupRamping()
                .useSpeedControl()
                .build();
        TalonSettings rightRearSpeedSettings = TalonSettingsBuilder.defaults()
                .withCurrentLimits(35, 30, 200)
                .coastInNeutral()
                .withDirections(true, true)
                .limitMotorOutputs(1.0, 0.2)
                .noMotorStartupRamping()
                .useSpeedControl()
                .build();

		speedModeTalons = new FourTalonsWithSettings(
		        frontLeftMotor,
		        rearLeftMotor,
		        frontRightMotor,
		        rearRightMotor,
		        leftFrontSpeedSettings,leftRearSpeedSettings,
		        rightFrontSpeedSettings,rightRearSpeedSettings);

		TalonSettings leftFrontPositionSettings = TalonSettingsBuilder.defaults()
				.withCurrentLimits(35, 30, 200)
				.brakeInNeutral()
				.withDirections(false, false)
				.limitMotorOutputs(1.0, 0.25)
				.noMotorStartupRamping()
				.usePositionControl()
				.withGains(0.3,5.0, 0.0, 0.0)
				.withMotionProfile(400, 500,POSITION_ENCODER_TOLERANCE)
				.build();
		TalonSettings leftRearPositionSettings = TalonSettingsBuilder.defaults()
                .withCurrentLimits(35, 30, 200)
                .brakeInNeutral()
                .withDirections(false, false)
                .limitMotorOutputs(1.0, 0.25)
                .noMotorStartupRamping()
                .usePositionControl()
                .withGains(0.3,5.0, 0.0, 0.0)
                .withMotionProfile(400, 500,POSITION_ENCODER_TOLERANCE)
                .build();
		TalonSettings rightFrontPositionSettings = TalonSettingsBuilder.defaults()
				.withCurrentLimits(35, 30, 200)
				.brakeInNeutral()
				.withDirections(false, true)
				.limitMotorOutputs(1.0, 0.15)
				.noMotorStartupRamping()
				.usePositionControl()
				.withGains(0.3,8.0, 0, 0.0)
				.withMotionProfile(400, 500,POSITION_ENCODER_TOLERANCE)
				.build();
		

        TalonSettings rightRearPositionSettings = TalonSettingsBuilder.defaults()
                .withCurrentLimits(35, 30, 200)
                .brakeInNeutral()
                .withDirections(true, true)
                .limitMotorOutputs(1.0, 0.15)
                .noMotorStartupRamping()
                .usePositionControl()
                .withGains(0.3,8.0, 0, 0.0)
                .withMotionProfile(400, 500,POSITION_ENCODER_TOLERANCE)
                .build();
		positionModeTalons = new FourTalonsWithSettings(
                frontLeftMotor,
                rearLeftMotor,
                frontRightMotor,
                rearRightMotor,
                leftFrontPositionSettings,
                leftRearPositionSettings,
                rightFrontPositionSettings,
                rightRearPositionSettings);
				
		arcadeDrive = new BasicArcadeDriveController(speedModeTalons, driveInstructionSource);
		positionDrive = new PositionDriveController(positionModeTalons, getPositionBuffer(), 
				        new EncoderInchesConverter(ENCODER_TICKS_PER_INCH));
		
	}

	public void setupNewDriveSource(DriveInstructionSource newSource) {
		arcadeDrive.setDriveInstructionSource(newSource);
	}
	public void restoreOriginalDriveSource() {
		arcadeDrive.setDriveInstructionSource(this.driveInstructionSource);
	}
	
	public void stop(){
	    frontLeftMotor.set(0.0);
	    rearLeftMotor.set(0.0);
        frontRightMotor.set(0.0);
        rearRightMotor.set(0.0);
	}

    public void resetCollision() {
        collisionDetected = false;
    }

    public boolean hasCollisionOccured() {
        if (this.navX != null) {
            double currWorldAccelX = navX.getWorldLinearAccelX();
            double currWorldAccelY = navX.getWorldLinearAccelY();
            double currJerkX = currWorldAccelX - lastWorldAccelX;
            double currJerkY = currWorldAccelY - lastWorldAccelY;
            lastWorldAccelX = currWorldAccelX;
            lastWorldAccelY = currWorldAccelY;

            if ( ( Math.abs(currJerkX) > COLLISION_THRESHOLD_DELTA_G ) ||
                 ( Math.abs(currJerkY) > COLLISION_THRESHOLD_DELTA_G ) ) {
                collisionDetected = true;
            }
            if ( ( Math.abs(this.navX.getPitch()) > TIP_THRESHOLD_DEGREES ) ||
                 ( Math.abs(this.navX.getRoll())  > TIP_THRESHOLD_DEGREES ) ) {
                collisionDetected = true;
            }
        }
        return collisionDetected;
    }

	@Override
	public void periodic() {
		dataLogger.log("DriveMode", driveMode + "");
		
		dataLogger.log("frontLeftEncoder", frontLeftMotor.getSelectedSensorPosition(0));
		dataLogger.log("frontRightEncoder", frontRightMotor.getSelectedSensorPosition(0));
		dataLogger.log("rearLeftEncoder", rearLeftMotor.getSelectedSensorPosition(0));
		dataLogger.log("rearRightEncoder", rearRightMotor.getSelectedSensorPosition(0));
        if (this.navX != null) {
            // dataLogger.log("NavX: ", this.navX);
            dataLogger.log("NavX Collision Detected: ", this.collisionDetected);
            dataLogger.log("NavX Pitch Angle (X): ", this.navX.getPitch());
            dataLogger.log("NavX Roll Angle (Y): ", this.navX.getRoll());
            dataLogger.log("NavX Yaw Angle (Z) : ", this.navX.getYaw());
        }
		
		if (driveMode == DriveMode.POSITION_DRIVE) {
			runController(positionDrive);
		} else if (driveMode == DriveMode.SPEED_DRIVE) {
			runController(arcadeDrive);
		} else {
			runController(doNothing);
		}
	}

}
