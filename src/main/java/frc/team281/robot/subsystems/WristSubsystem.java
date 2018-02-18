package frc.team281.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team281.robot.RobotMap.PCM;

public class WristSubsystem extends BaseSubsystem {

    private Solenoid up;
    private Solenoid down;

    private DigitalInput limitSwitch;
    
    public WristSubsystem() {
    }

    @Override
    public void initialize() {
        up = new Solenoid(PCM.Wrist.UP);
        down = new Solenoid(PCM.Wrist.DOWN);

    }

    public void pivotUp() {
        // trigger up solenoid
    }
    
    public void pivotDown() {
        // trigger down solenoid
    }
    
}
