package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.team281.robot.RobotMap;

public class LifterSubsystem extends BaseSubsystem {
    
    private WPI_TalonSRX motorOne;
    private WPI_TalonSRX motorTwo;
    
    public static final double FULL_SPEED_PERCENT=100;
    public static final double HOMING_SPEED_PERCENT=20;
    
    public static final double MAX_ENCODER_COUNTS=10000;
    public static final double ENCODER_CLICKS_PER_INCH=40;
    public static final double CANT_READ_HEIGHT=-1.0;
    public static final double MIN_HEIGHT_INCHES = 1.0;
    public static final double MAX_HEIGHT_INCHES = 100;
    
    private DigitalInput limitSwitch;
    
    public LifterSubsystem() {
    }


    
    @Override
    public void initialize() {
        motorOne = new WPI_TalonSRX(RobotMap.CAN.Lifter.MOTOR_ONE);
        motorTwo = new WPI_TalonSRX(RobotMap.CAN.Lifter.MOTOR_TWO);        
        limitSwitch = new DigitalInput(RobotMap.DigitalIO.LIFTER_AT_BOTTOM);
    }

    public double trimHeight( double heightInches){
        if ( heightInches > MAX_HEIGHT_INCHES){
            return MAX_HEIGHT_INCHES;
        }
        else if ( heightInches < MIN_HEIGHT_INCHES){
            return MIN_HEIGHT_INCHES;
        }
        else{
            return heightInches;
        }
    }
    
    public void motorsUp(double speedPercent){
        motorOne.set(ControlMode.PercentOutput,-speedPercent);
        motorTwo.set(ControlMode.PercentOutput,speedPercent);  
    }
    public void motorsDown(double speedPercent){
        motorOne.set(ControlMode.PercentOutput,speedPercent);
        motorTwo.set(ControlMode.PercentOutput,-speedPercent);        
    }
    
    public void motorsOff(){
        motorOne.set(ControlMode.Disabled,0);
        motorTwo.set(ControlMode.Disabled,0);
    }    
    
    public void setZeroPosition(){
        motorOne.setSelectedSensorPosition(0, 0, 0);
        motorTwo.setSelectedSensorPosition(0, 0, 0);
    }
    public double getHeightInches(){
        int motorOneCounts = motorOne.getSelectedSensorPosition(0);
        int motorTwoCounts = motorTwo.getSelectedSensorPosition(0);
        
        if ( motorOneCounts > 0 ){
            return convertEncoderCountsToInches(motorOneCounts);
        }
        else if ( motorTwoCounts > 0 ){
            return convertEncoderCountsToInches(motorTwoCounts);
        }
        else{
           return CANT_READ_HEIGHT;
        }
    }
    
    public double convertEncoderCountsToInches(int encoderCounts){
        return (double)encoderCounts / ENCODER_CLICKS_PER_INCH;
    }
    
    public boolean isLifterAtBottom(){
        return limitSwitch.get();
    }
    
}
