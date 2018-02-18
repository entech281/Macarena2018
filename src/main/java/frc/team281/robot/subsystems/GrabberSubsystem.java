package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.team281.robot.RobotMap;

public class GrabberSubsystem extends BaseSubsystem {
    
    private WPI_TalonSRX motorLeft;
    private WPI_TalonSRX motorRight;
    
    public GrabberSubsystem() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize() {
        motorLeft = new WPI_TalonSRX(RobotMap.CAN.Grabber.MOTOR_LEFT);
        motorRight = new WPI_TalonSRX(RobotMap.CAN.Grabber.MOTOR_RIGHT);

    }

}
