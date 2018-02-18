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
    
    private DoubleSolenoid leftSolenoid = new DoubleSolenoid(PCM.Grabber.LEFT_INSIDE, PCM.Grabber.LEFT_OUTSIDE);
    private DoubleSolenoid rightSolenoid = new DoubleSolenoid(PCM.Grabber.RIGHT_INSIDE, PCM.Grabber.RIGHT_OUTSIDE);
    
    public GrabberSubsystem() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize() {
        motorLeft = new WPI_TalonSRX(RobotMap.CAN.Grabber.MOTOR_LEFT);
        motorRight = new WPI_TalonSRX(RobotMap.CAN.Grabber.MOTOR_RIGHT);

    }

}
