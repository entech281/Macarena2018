package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team281.robot.RobotMap;
import frc.team281.robot.RobotMap.DigitalIO;
import frc.team281.robot.RobotMap.PCM;
import frc.team281.robot.controllers.TalonSpeedController;

public class GrabberSubsystem extends BaseSubsystem {
    
    private WPI_TalonSRX leftMotor;
    private WPI_TalonSRX rightMotor;
    private TalonSpeedController leftMotorController;
    private TalonSpeedController rightMotorController;
    
    public static final double MOTOR_CONFIG=1;
    public static final double LEFT_LOAD_PERCENT = 100;
    public static final double RIGHT_LOAD_PERCENT = 90;
    public static final double SHOOT_PERCENT = 100;
    
    private DigitalInput limitSwitch;
    
    private DoubleSolenoid solenoid;

    
    public GrabberSubsystem() {
        
    }

    @Override
    public void initialize() {
        dataLogger.warn("Grabber Subsystem Init");
        leftMotor = new WPI_TalonSRX(RobotMap.CAN.Grabber.MOTOR_LEFT);
        rightMotor = new WPI_TalonSRX(RobotMap.CAN.Grabber.MOTOR_RIGHT);

        solenoid = new DoubleSolenoid(RobotMap.CAN.PC_MODULE, PCM.Grabber.INSIDE, PCM.Grabber.OUTSIDE);
        
        limitSwitch = new DigitalInput(DigitalIO.GRABBER_CUBE_LOADED);
        
        TalonSettings leftMotorSettings = TalonSettingsBuilder
                .defaults()
                .withCurrentLimits(10, 5, 200)
                .brakeInNeutral()
                .defaultDirectionSettings()
                .noMotorOutputLimits()
                .noMotorStartupRamping()
                .useSpeedControl()
                .build();
        
        TalonSettings rightMotorSettings = TalonSettingsBuilder.inverted(leftMotorSettings);
        leftMotorController = new TalonSpeedController(leftMotor, leftMotorSettings);
        rightMotorController = new TalonSpeedController(rightMotor, rightMotorSettings);        
        dataLogger.warn("Grabber Subsystem Init Finished");
    }

    public boolean isCubeTouchingSwitch() {
        return ! limitSwitch.get();
    }
    
    public void startLoading() {
        if ( isCubeTouchingSwitch() ){
            dataLogger.warn("Cannot Load-- already at limit");
        }
        else{
            leftMotorController.setDesiredSpeed(-MOTOR_CONFIG*LEFT_LOAD_PERCENT);
            rightMotorController.setDesiredSpeed(-MOTOR_CONFIG*RIGHT_LOAD_PERCENT);              
        }
      
    }
    
    public void startShooting() {
        leftMotorController.setDesiredSpeed(MOTOR_CONFIG*LEFT_LOAD_PERCENT);
        rightMotorController.setDesiredSpeed(MOTOR_CONFIG*RIGHT_LOAD_PERCENT);  
    }
    
    @Override
    public void periodic() {
        dataLogger.log("IsCubeLoaded",isCubeTouchingSwitch());
        if ( isCubeTouchingSwitch() ){
            stopMotors();
        }
    }

    public void stopMotors() {
        leftMotorController.setDesiredSpeed(0);
        rightMotorController.setDesiredSpeed(0);  
    }
    
    public void solenoidOff() {
        solenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    public void open() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void close() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }
    
}
