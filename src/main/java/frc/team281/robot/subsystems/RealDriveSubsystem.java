package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team281.robot.DriveInstructionSource;
import frc.team281.robot.RobotMap;
import frc.team281.robot.controllers.TalonControllerGroup;
import frc.team281.robot.subsystems.DriveSystemMode.StateResult;

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

    public static final int MOTOR_CRUISE_VELOCITY = 1200;
    public static final int MOTOR_ACCELERATION = 800;
    public static final double I_GAIN = 0.0;
    public static final double P_GAIN = 0.1;
    public static final double D_GAIN = 0.0;
    public static final double F_GAIN = 0.0;

    // for speed control
    private DifferentialDrive drive;

    // for position control
    private TalonControllerGroup positionControllerGroup;
    private FourWheelTankTalonCalibrator calibrator = new FourWheelTankTalonCalibrator();

    // have to hold on to these to change control modes.
    private WPI_TalonSRX frontLeftMotor;
    private WPI_TalonSRX frontRightMotor;
    private WPI_TalonSRX rearLeftMotor;
    private WPI_TalonSRX rearRightMotor;

    private TalonSettings leftTalonSettings;
    private TalonSettings rightTalonSettings;

    // estimated based on 6" diameter wheels, with 80 counts per turn, gear ratio
    // 14/52 * 14/52 13.8:1
    private EncoderInchesConverter encoderConverter = new EncoderInchesConverter(46.0);

    public RealDriveSubsystem(DriveInstructionSource driveInstructionSource) {
        super(driveInstructionSource);
    }

    @Override
    public void initialize() {

        this.frontLeftMotor = new WPI_TalonSRX(RobotMap.CAN.FRONT_LEFT_MOTOR);
        this.frontRightMotor = new WPI_TalonSRX(RobotMap.CAN.FRONT_RIGHT_MOTOR);
        this.rearLeftMotor = new WPI_TalonSRX(RobotMap.CAN.REAR_LEFT_MOTOR);
        this.rearRightMotor = new WPI_TalonSRX(RobotMap.CAN.REAR_RIGHT_MOTOR);

        this.leftTalonSettings = TalonSettingsBuilder.defaults()
                .withCurrentLimits(35, 30, 200)
                .coastInNeutral()
                .withDirections(false, false)
                .limitMotorOutputs(0.5, 0.01)
                .noMotorStartupRamping()
                .usePositionControl()
                .withGains(F_GAIN, P_GAIN, I_GAIN, D_GAIN)
                .withMotionProfile(MOTOR_CRUISE_VELOCITY, MOTOR_ACCELERATION)
                .build();

        this.rightTalonSettings = TalonSettingsBuilder.inverted(leftTalonSettings, false, true);

    }

    /**
     * 
     * This method tests that the encoders are working by driving forward a bit, and
     * then making sure we read the encoders
     */
    public void startCalibration() {
        StateResult r = driveMode.enterCalibrate();
        if (r == StateResult.ENTERED) {
            calibrator.startCalibration(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor,
                    leftTalonSettings, rightTalonSettings);
        }
    }

    public void finishCalibration() {
        this.positionControllerGroup = calibrator.finishCalibration();
        driveMode.finishCalibrating();
    }

    protected boolean enterSpeedMode() {
        StateResult r = driveMode.enterSpeed();
        if (r == StateResult.REJECTED) {
            return false;
        }
        if (r == StateResult.ENTERED) {
            drive = new DifferentialDrive(new SpeedControllerGroup(frontLeftMotor, rearLeftMotor),
                    new SpeedControllerGroup(frontRightMotor, rearRightMotor));
        }
        return true;

    }

    protected boolean enterPositionMode() {
        StateResult r = driveMode.enterPosition();
        if (r == StateResult.REJECTED) {
            return false;
        }
        if (r == StateResult.ENTERED) {
            positionControllerGroup.resetMode();
        }
        return true;
    }

    public void stop() {
        if (enterSpeedMode()) {
            drive.tankDrive(0., 0.);
        }

    }

    public void arcadeDrive(double forw, double turn) {
        if (enterSpeedMode()) {
            drive.arcadeDrive(-forw, turn, true);
        }
    }

    public void tankDrive(double left, double right) {
        if (enterSpeedMode()) {
            drive.tankDrive(left, right, true);
        }
    }

    @Override
    public void drive(Position desiredPosition) {
        if (enterPositionMode()) {
            int encoderCountsLeft = encoderConverter.toCounts(desiredPosition.getLeftInches());
            int encoderCountsRight = encoderConverter.toCounts(desiredPosition.getRightInches());
            positionControllerGroup.setDesiredPosition(encoderCountsLeft, encoderCountsRight);
        }

    }

    @Override
    public Position getCurrentPosition() {

        int leftEncoderCount = positionControllerGroup.computeLeftEncoderCounts();
        int rightEncoderCount = positionControllerGroup.computeRightEncoderCounts();

        double leftInches = encoderConverter.toInches(leftEncoderCount);
        double rightInches = encoderConverter.toInches(rightEncoderCount);

        // use the average
        return new Position(leftInches, rightInches);

    }

}
