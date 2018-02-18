package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.team281.robot.RobotMap;

public class LifterSubsystem extends BaseSubsystem {
    
    private WPI_TalonSRX motorOne;
    private WPI_TalonSRX motorTwo;
    
    //TODO; encoder
    
    private DigitalInput limitSwitch;
    
    public LifterSubsystem() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize() {
        motorOne = new WPI_TalonSRX(RobotMap.CAN.Lifter.MOTOR_ONE);
        motorTwo = new WPI_TalonSRX(RobotMap.CAN.Lifter.MOTOR_TWO);

    }

}
