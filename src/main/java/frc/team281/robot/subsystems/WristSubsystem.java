package frc.team281.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team281.robot.RobotMap.PCM;

public class WristSubsystem extends BaseSubsystem {

    private Solenoid up = new Solenoid(PCM.Wrist.UP);
    private Solenoid down = new Solenoid(PCM.Wrist.DOWN);

    private DigitalInput limitSwitch;
    
    public WristSubsystem() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub

    }

}
