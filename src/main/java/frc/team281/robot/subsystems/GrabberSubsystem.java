package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team281.robot.RobotMap;
import frc.team281.robot.RobotMap.DigitalIO;
import frc.team281.robot.RobotMap.PCM;

public class GrabberSubsystem extends BaseSubsystem {
    
    private WPI_TalonSRX motorLeft;
    private WPI_TalonSRX motorRight;
    
    public static final double LEFT_LOAD_PERCENT = 100;
    public static final double RIGHT_LOAD_PERCENT = 90;
    public static final double SHOOT_PERCENT = 100;
    
    private DigitalInput limitSwitch;
    
    private DoubleSolenoid leftSolenoid;
    private DoubleSolenoid rightSolenoid;
    
    public GrabberSubsystem() {
    }

    @Override
    public void initialize() {
        motorLeft = new WPI_TalonSRX(RobotMap.CAN.Grabber.MOTOR_LEFT);
        motorRight = new WPI_TalonSRX(RobotMap.CAN.Grabber.MOTOR_RIGHT);

        leftSolenoid = new DoubleSolenoid(PCM.Grabber.LEFT_INSIDE, PCM.Grabber.LEFT_OUTSIDE);
        rightSolenoid = new DoubleSolenoid(PCM.Grabber.RIGHT_INSIDE, PCM.Grabber.RIGHT_OUTSIDE);
        
        limitSwitch = new DigitalInput(DigitalIO.GRABBER_CUBE_LOADED);
    }

    public boolean isCubeTouchingSwitch() {
        return limitSwitch.get();
    }
    
    public void startLoading() {
        // run the motors until the switch is tripped then stop the motors
        motorLeft.set(ControlMode.PercentOutput, LEFT_LOAD_PERCENT);
        motorRight.set(ControlMode.PercentOutput, RIGHT_LOAD_PERCENT);
    }
    
    public void startShooting() {
        // run motors for N seconds, N is some constant
        motorLeft.set(ControlMode.PercentOutput, SHOOT_PERCENT);
        motorRight.set(ControlMode.PercentOutput, SHOOT_PERCENT);
    }
    
    public void stopMotors() {
        motorLeft.set(ControlMode.Disabled, 0);
        motorRight.set(ControlMode.Disabled, 0);
    }
    
    public void solenoidsOff() {
        leftSolenoid.set(DoubleSolenoid.Value.kOff);
        rightSolenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    public void open() {
        leftSolenoid.set(DoubleSolenoid.Value.kForward);
        rightSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void close() {
        leftSolenoid.set(DoubleSolenoid.Value.kReverse);
        rightSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
}
