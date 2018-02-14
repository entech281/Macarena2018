package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.team281.robot.RobotMap;
import frc.team281.robot.controllers.EncoderCheck;
import frc.team281.robot.controllers.TalonControllerGroup;
import frc.team281.robot.controllers.TalonPositionController;
import frc.team281.robot.controllers.TalonSpeedController;

/**
 * Given four CAN Talons and some desired settings, gives a controller group
 * that will control the position, even if some of the encoders are broken
 * 
 * @author dcowden
 *
 */
public class FourWheelTankTalonCalibrator {

    private boolean calibrated = false;
    private MotorEncoderTester tester = null;
    private TalonSettings leftTalonSettings;
    private TalonSettings rightTalonSettings;

    private WPI_TalonSRX frontLeftMotor;
    private WPI_TalonSRX rearLeftMotor;
    private WPI_TalonSRX frontRightMotor;
    private WPI_TalonSRX rearRightMotor;

    public boolean isCalibrated() {
        return calibrated;
    }

    public void startCalibration(WPI_TalonSRX frontLeftMotor, WPI_TalonSRX rearLeftMotor, WPI_TalonSRX frontRightMotor,
            WPI_TalonSRX rearRightMotor, TalonSettings leftTalonSettings, TalonSettings rightTalonSettings) {

        this.leftTalonSettings = leftTalonSettings;
        this.rightTalonSettings = rightTalonSettings;
        this.frontLeftMotor = frontLeftMotor;
        this.rearLeftMotor = rearLeftMotor;
        this.frontRightMotor = frontRightMotor;
        this.rearRightMotor = rearRightMotor;

        tester = new MotorEncoderTester(new TalonSpeedController(frontLeftMotor, leftTalonSettings),
                new TalonSpeedController(rearLeftMotor, leftTalonSettings),
                new TalonSpeedController(frontRightMotor, rightTalonSettings),
                new TalonSpeedController(rearRightMotor, rightTalonSettings));

        tester.startTest();

    }

    public TalonControllerGroup finishCalibration() {

        // unless we detect problems, left and right settings match
        TalonSettings frontLeftSettings = TalonSettingsBuilder.copy(leftTalonSettings);
        TalonSettings frontRightSettings = TalonSettingsBuilder.copy(rightTalonSettings);
        TalonSettings rearLeftSettings = TalonSettingsBuilder.copy(leftTalonSettings);
        TalonSettings rearRightSettings = TalonSettingsBuilder.copy(rightTalonSettings);

        EncoderCheck checker = tester.finishTest();

        if (checker.shouldDisableAll()) {
            frontLeftSettings = TalonSettingsBuilder.disabledCopy(rearLeftSettings);
            frontRightSettings = TalonSettingsBuilder.disabledCopy(rearLeftSettings);
            rearLeftSettings = TalonSettingsBuilder.disabledCopy(rearLeftSettings);
            rearRightSettings = TalonSettingsBuilder.disabledCopy(rearLeftSettings);
        } else {
            if (checker.shouldLeftFrontFollowLeftRear()) {
                frontLeftSettings = TalonSettingsBuilder.follow(rearLeftSettings, RobotMap.CAN.FRONT_RIGHT_MOTOR);
            }
            if (checker.shouldLeftRearFollowLeftFront()) {
                rearLeftSettings = TalonSettingsBuilder.follow(frontLeftSettings, RobotMap.CAN.FRONT_LEFT_MOTOR);
            }
            if (checker.shouldRightFrontFollowRightRear()) {
                frontRightSettings = TalonSettingsBuilder.follow(rearRightSettings, RobotMap.CAN.FRONT_RIGHT_MOTOR);
            }
            if (checker.shouldRightRearFollowRightFront()) {
                rearRightSettings = TalonSettingsBuilder.follow(frontRightSettings, RobotMap.CAN.FRONT_RIGHT_MOTOR);
            }
        }
        this.calibrated = true;
        return new TalonControllerGroup(new TalonPositionController(frontLeftMotor, frontLeftSettings),
                new TalonPositionController(frontRightMotor, rearLeftSettings),
                new TalonPositionController(rearLeftMotor, frontRightSettings),
                new TalonPositionController(rearRightMotor, rearRightSettings));

    }

}
