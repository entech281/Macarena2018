package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.team281.robot.RobotMap;
import frc.team281.robot.RobotMap.PCM;

public class GrabberSubsystem extends BaseSubsystem {
    
    private WPI_TalonSRX motorLeft;
    private WPI_TalonSRX motorRight;
    
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
        
    }

    public void load() {
        // run the motors until the switch is tripped then stop the motors
    }
    
    public void shoot() {
        // run motors for N seconds, N is some constant
    }
    
    public void stop() {
        // stop the motors
    }
    
    public void open() {
        // turn the solenoids to the N side direction
    }
    
    public void close() {
        // turn the solenoids to the outside direction (opposite of N)
    }
    
}
