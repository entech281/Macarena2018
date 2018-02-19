package frc.team281.robot.subsystems;

public class FakeLifterSubsystem extends LifterSubsystem {

    protected double currentHeight= 0;
    
    public static final double INCHES_PER_CLICK=0.25;
    protected double moveSpeed = 0.0;
    
    private FakeMotorStatus status = FakeMotorStatus.STOPPED;
    public enum FakeMotorStatus{
        MOVING_UP,
        MOVING_DOWN,
        STOPPED
    }
    
    @Override
    public void motorsUp(double speedPercent) {
        status = FakeMotorStatus.MOVING_UP;
        moveSpeed = speedPercent*INCHES_PER_CLICK;
    }

    @Override
    public void motorsDown(double speedPercent) {
        status = FakeMotorStatus.MOVING_DOWN;
        moveSpeed = speedPercent*INCHES_PER_CLICK;
    }

    @Override
    public void motorsOff() {
        status = FakeMotorStatus.STOPPED;
        moveSpeed = 0;
    }

    @Override
    public boolean isLifterAtBottom() {
        return currentHeight <= 0;
    }

    @Override
    public void setZeroPosition() {
        currentHeight = 0;
    }

    @Override
    public double getHeightInches() {
        switch ( status){
        case MOVING_DOWN:
            currentHeight -= moveSpeed;
        case MOVING_UP:
            currentHeight += moveSpeed;
        case STOPPED:
            break;
        default:
            break;
        
        }
        currentHeight = trimHeight(currentHeight);
        return currentHeight;
    }

}
