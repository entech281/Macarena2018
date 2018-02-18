package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.team281.robot.RobotMap;

public class LifterSubsystem extends BaseSubsystem {
    
    private WPI_TalonSRX motorOne;
    private WPI_TalonSRX motorTwo;
    
    public LifterSubsystem() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize() {
        motorOne = new WPI_TalonSRX(RobotMap.CAN.LIFTER_MOTOR_ONE);
        motorTwo = new WPI_TalonSRX(RobotMap.CAN.LIFTER_MOTOR_TWO);

    }

}
