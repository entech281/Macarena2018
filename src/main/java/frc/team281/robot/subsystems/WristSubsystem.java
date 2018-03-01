package frc.team281.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team281.robot.RobotMap;
import frc.team281.robot.RobotMap.PCM;

public class WristSubsystem extends BaseSubsystem {

    private Solenoid up;
    private Solenoid down;

    //2 private DoubleSolenoid solenoid;

    private boolean wristUp;

    public WristSubsystem() {
    }

    @Override
    public void initialize() {
        //2 solenoid = new DoubleSolenoid(RobotMap.CAN.PC_MODULE, PCM.Wrist.UP, PCM.Wrist.DOWN);
        wristUp = true;
        up = new Solenoid(RobotMap.CAN.PC_MODULE, PCM.Wrist.UP);
        down = new Solenoid(RobotMap.CAN.PC_MODULE,PCM.Wrist.DOWN);

    }

    @Override
    public void periodic() {
        //2 if (wristUp) {
        //2     solenoid.set(DoubleSolenoid.Value.kReverse);
        //2 } else {
        //2     solenoid.set(DoubleSolenoid.Value.kForward);
        //2 }
    }

    public void pivotUp() {
        wristUp = true;
        up.set(true);
        down.set(true);
    }
    
    public void pivotDown() {
        wristUp = false;
        up.set(false);
        down.set(false);
    }
    public boolean wristAtBottom() {
        return limitSwitch.get();
    }
}
