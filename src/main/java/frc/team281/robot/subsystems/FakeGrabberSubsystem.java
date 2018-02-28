package frc.team281.robot.subsystems;



public class FakeGrabberSubsystem extends GrabberSubsystem {



    protected int cubeIntakeCount = 0;
    protected boolean cubeSwitchTouching = false;
    public static final int TIME_TO_LOAD_CUBE_MILLIS=2000;
    public static final int LOOP_TIME_MILLIS=20;
    
    @Override
    public void initialize() {
    }

    @Override
    public boolean isCubeTouchingSwitch() {
        cubeIntakeCount += LOOP_TIME_MILLIS;
        if (cubeIntakeCount > TIME_TO_LOAD_CUBE_MILLIS){
            cubeSwitchTouching= true;
        }
        return cubeSwitchTouching;
    }

    @Override
    public void startLoading() {
        dataLogger.log("MotorsRunning",true);
        cubeSwitchTouching = false;
    }

    @Override
    public void startShooting() {
        dataLogger.log("MotorsRunning",true);
        cubeSwitchTouching = false;
    }

    @Override
    public void stopMotors() {
        dataLogger.log("MotorsRunning",false);
    }

    @Override
    public void open() {
        dataLogger.log("GrabberClosed",false);
    }

    @Override
    public void close() {
        dataLogger.log("GrabberClosed",true);
    }

}
