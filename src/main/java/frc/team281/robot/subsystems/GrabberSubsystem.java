package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
    private boolean isLoading = false;
    private boolean isShooting = false;

    
    public GrabberSubsystem() {
        super();
    }

    @Override
    public void initialize() {
        dataLogger.log(this);
        dataLogger.warn("Grabber Subsystem Init");
        leftMotor = new WPI_TalonSRX(RobotMap.CAN.Grabber.MOTOR_LEFT);
        rightMotor = new WPI_TalonSRX(RobotMap.CAN.Grabber.MOTOR_RIGHT);

        solenoid = new DoubleSolenoid(RobotMap.CAN.PC_MODULE, PCM.Grabber.INSIDE, PCM.Grabber.OUTSIDE);
        
        limitSwitch = new DigitalInput(DigitalIO.GRABBER_CUBE_LOADED);
        
        TalonSettings leftMotorSettings = TalonSettingsBuilder
                .defaults()
                .withCurrentLimits(10, 5, 200)
                .brakeInNeutral()
                .withDirections(false, false)
                .noMotorOutputLimits()
                .noMotorStartupRamping()
                .useSpeedControl()
                .build();
        
        TalonSettings rightMotorSettings = TalonSettingsBuilder
                .defaults()
                .withCurrentLimits(10, 5, 200)
                .brakeInNeutral()
                .withDirections(false, true)
                .noMotorOutputLimits()
                .noMotorStartupRamping()
                .useSpeedControl()
                .build();        
        
        
        leftMotorController = new TalonSpeedController(leftMotor, leftMotorSettings);
        rightMotorController = new TalonSpeedController(rightMotor, rightMotorSettings);
        
        leftMotorController.configure();
        rightMotorController.configure();
        
        //leftMotorController.getTalon().set(ControlMode.PercentOutput, 0.0);
        //rightMotorController.getTalon().set(ControlMode.PercentOutput, 0.0);
        
        dataLogger.warn("Grabber Subsystem Init Finished");
    }

    public boolean isCubeTouchingSwitch() {
        return ! limitSwitch.get();
    }
    
    public void startLoading() {
        if ( isCubeTouchingSwitch() ){
            dataLogger.warn("Cannot Load-- already at limit");
            stopMotors();
        }
        else{
        	isLoading = true;
            leftMotorController.setDesiredSpeed(LEFT_LOAD_PERCENT);
            rightMotorController.setDesiredSpeed(RIGHT_LOAD_PERCENT);              
        }
      
    }
    
    public void startShooting() {
    	isShooting = true;
        leftMotorController.setDesiredSpeed(-SHOOT_PERCENT);
        rightMotorController.setDesiredSpeed(-SHOOT_PERCENT);  
    }
    
    @Override
    public void periodic() {
        dataLogger.log("IsCubeLoaded",isCubeTouchingSwitch());
        dataLogger.log("LeftMotorMode", leftMotorController.getTalon().getControlMode()+"");
        dataLogger.log("RightMotorMode", rightMotorController.getTalon().getControlMode()+"");
        
        if (isShooting) {
            startShooting();
        }
        if (isLoading) {
            if ( isCubeTouchingSwitch() ){
                stopMotors();
            } else {
            	startLoading();
            }
        }
    }

    public void stopMotors() {
    	    isLoading = false;
    	    isShooting = false;
        leftMotorController.setDesiredSpeed(0);
        rightMotorController.setDesiredSpeed(0);  
    }
    
    public void solenoidOff() {
        solenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    public void open() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void close() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
}
