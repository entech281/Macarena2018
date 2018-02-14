package frc.team281.robot.controllers;

/**
 * Checks to see how we should configure motors, based on encoder readings
 * 
 * @author dcowden
 *
 */
public class EncoderCheck {

    private int leftRearCounts = 0;
    private int rightRearCounts = 0;
    private int leftFrontCounts = 0;
    private int rightFrontCounts = 0;

    public EncoderCheck(int leftRearCounts, int leftFrontCounts, int rightFrontCounts, int rightRearCounts) {
        this.leftFrontCounts = leftFrontCounts;
        this.rightFrontCounts = rightFrontCounts;
        this.rightRearCounts = rightRearCounts;
        this.leftRearCounts = leftRearCounts;

    }

    public boolean shouldLeftFrontFollowLeftRear() {
        return hasProblems() && canDrive() && isLeftRearOk() && (!isLeftFrontOk());
    }

    public boolean shouldLeftRearFollowLeftFront() {
        return hasProblems() && canDrive() && isLeftFrontOk() && (!isLeftRearOk());
    }

    public boolean shouldRightRearFollowRightFront() {
        return hasProblems() && canDrive() && isRightFrontOk() && (!isRightRearOk());
    }

    public boolean shouldRightFrontFollowRightRear() {
        return hasProblems() && canDrive() && isRightRearOk() && (!isRightFrontOk());
    }

    public boolean shouldDisableAll() {
        return this.hasProblems() && !this.canDrive();
    }

    public boolean isLeftRearOk() {
        return this.leftRearCounts > 0;
    }

    public boolean isLeftFrontOk() {
        return this.leftFrontCounts > 0;
    }

    public boolean isRightRearOk() {
        return this.rightRearCounts > 0;
    }

    public boolean isRightFrontOk() {
        return this.rightFrontCounts > 0;
    }

    public boolean isLeftOk() {
        return isLeftFrontOk() || isLeftRearOk();
    }

    public boolean isRightOk() {
        return isRightFrontOk() || isRightRearOk();
    }

    public boolean canDrive() {
        return isLeftOk() && isRightOk();
    }

    public boolean hasLeftProblems() {
        return !isLeftOk();
    }

    public boolean hasRightProblems() {
        return !isRightOk();
    }

    public boolean hasProblems() {
        return !allOk();
    }

    public boolean allOk() {
        return isLeftRearOk() && isLeftFrontOk() && isRightRearOk() && isRightFrontOk();

    }
}
