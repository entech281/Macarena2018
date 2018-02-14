package frc.team281.robot.controllers;

/**
 * Utilities for operating on a group of controllers
 * 
 * @author dcowden
 *
 */
public class TalonControllerGroup {

    private TalonPositionController frontLeft;
    private TalonPositionController frontRight;
    private TalonPositionController rearLeft;
    private TalonPositionController rearRight;

    public TalonControllerGroup(TalonPositionController frontLeft, TalonPositionController frontRight,
            TalonPositionController rearLeft, TalonPositionController rearRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
    }

    public void resetMode() {
        frontLeft.resetMode();
        frontRight.resetMode();
        rearLeft.resetMode();
        rearRight.resetMode();
    }

    public void setDesiredPosition(int leftPosition, int rightPosition) {
        frontLeft.setDesiredPosition(leftPosition);
        rearLeft.setDesiredPosition(leftPosition);
        frontRight.setDesiredPosition(rightPosition);
        rearRight.setDesiredPosition(rightPosition);
    }

    public int computeLeftEncoderCounts() {
        int total = 0;
        int count = 0;
        Integer pos = frontLeft.getActualPosition();
        if (pos != null) {
            total += pos;
            count += 1;
        }
        pos = rearLeft.getActualPosition();
        if (pos != null) {
            total += pos;
            count += 1;
        }
        return total / count;
    }

    public int computeRightEncoderCounts() {
        int total = 0;
        int count = 0;
        Integer pos = frontRight.getActualPosition();
        if (pos != null) {
            total += pos;
            count += 1;
        }
        pos = rearRight.getActualPosition();
        if (pos != null) {
            total += pos;
            count += 1;
        }
        return total / count;
    }
}
