package frc.team281.robot.subsystems;

public class FakeWristSubsystem extends WristSubsystem {

    protected boolean wristUp = false;
    
    @Override
    public void initialize() {

    }

    @Override
    public void pivotUp() {
        wristUp = true;
        dataLogger.log("WristUp",wristUp);
    }

    @Override
    public void pivotDown() {
        wristUp = false;
        dataLogger.log("WristUp",wristUp);
    }

}
