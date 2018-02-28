package frc.team281.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team281.robot.RobotMap;
import frc.team281.robot.RobotMap.PCM;

public class WristSubsystem extends BaseSubsystem {

    //private Solenoid up;
    //private Solenoid down;

    private DoubleSolenoid solenoid;
    private DigitalInput limitSwitch;
    
    public WristSubsystem() {
    }

    @Override
    public void initialize() {
        solenoid = new DoubleSolenoid(RobotMap.CAN.PC_MODULE, PCM.Wrist.UP, PCM.Wrist.DOWN);
        //up = new Solenoid(RobotMap.CAN.PC_MODULE, PCM.Wrist.UP);
        //down = new Solenoid(RobotMap.CAN.PC_MODULE,PCM.Wrist.DOWN);

    }

    public void pivotUp() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
        //up.set(true);
        //down.set(false);
    }
    
    public void pivotDown() {
        solenoid.set(DoubleSolenoid.Value.kForward);
        //up.set(false);
        //down.set(true);
    }
    public boolean wristAtBottom() {
        return limitSwitch.get();
    }
    
}
