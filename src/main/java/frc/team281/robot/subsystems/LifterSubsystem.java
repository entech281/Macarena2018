package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.team281.robot.RobotMap;
import frc.team281.robot.controllers.TalonSpeedController;

public class LifterSubsystem extends BaseSubsystem {

    private WPI_TalonSRX motorOne;
    private WPI_TalonSRX motorTwo;
    private TalonSpeedController motorOneController;
    private TalonSpeedController motorTwoController;

    public static final double FULL_SPEED_PERCENT = 100;
    public static final double HOMING_SPEED_PERCENT = 20;

    public static final double MIN_HEIGHT_INCHES = 1.0;
    public static final double MAX_HEIGHT_INCHES = 100;

    private DigitalInput bottomLimitSwitch;
    private DigitalInput topLimitSwitch;

    public LifterSubsystem() {

    }

    @Override
    public void initialize() {
        motorOne = new WPI_TalonSRX(RobotMap.CAN.Lifter.MOTOR_ONE);
        motorTwo = new WPI_TalonSRX(RobotMap.CAN.Lifter.MOTOR_TWO);
        bottomLimitSwitch = new DigitalInput(RobotMap.DigitalIO.LIFTER_AT_BOTTOM);
        topLimitSwitch = new DigitalInput(RobotMap.DigitalIO.LIFTER_AT_TOP);
        TalonSettings motorOneSettings = TalonSettingsBuilder.defaults()
                .withCurrentLimits(20, 15, 200)
                .brakeInNeutral()
                .defaultDirectionSettings()
                .noMotorOutputLimits()
                .noMotorStartupRamping()
                .useSpeedControl()
                .build();

        TalonSettings motorTwoSettings = TalonSettingsBuilder.inverted(motorOneSettings);
        motorOneController = new TalonSpeedController(motorOne, motorOneSettings);
        motorTwoController = new TalonSpeedController(motorTwo, motorTwoSettings);

    }

    public void motorsUp(double speedPercent) {
        motorOneController.setDesiredSpeed(speedPercent);
        motorTwoController.setDesiredSpeed(-speedPercent);

    }

    public void motorsDown(double speedPercent) {
        motorOneController.setDesiredSpeed(-speedPercent);
        motorTwoController.setDesiredSpeed(speedPercent);
    }

    public void motorsOff() {
        motorOneController.setDesiredSpeed(0);
        motorTwoController.setDesiredSpeed(0);
    }

    @Override
    public void periodic() {
        // if ( limitSwitch.get() ){
        // motorsOff();
        // }
    }

    public boolean isLifterAtBottom() {
        return bottomLimitSwitch.get();
    }

    public boolean isLifterAtTop() {
        return topLimitSwitch.get();
    }
}
