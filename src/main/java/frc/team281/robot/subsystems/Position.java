package frc.team281.robot.subsystems;

public class Position {

    private double leftInches = 0;
    private double rightInches = 0;

    public Position(double leftInches, double rightInches) {
        this.leftInches = leftInches;
        this.rightInches = rightInches;
    }

    public double getLeftInches() {
        return leftInches;
    }

    public double getRightInches() {
        return rightInches;
    }

    public boolean isCloseTo(Position other, double tolerance) {
        // ideally use real distance, i'm lazy
        return (Math.abs(this.getLeftInches() - other.getLeftInches()) < tolerance
                && Math.abs(this.getRightInches() - other.getRightInches()) < tolerance);
    }
}
