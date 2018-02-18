package frc.team281.robot.subsystems;

public class Position {

	private double leftInches;
	private double rightInches;
	private boolean relative = true;

	public Position(double leftInches, double rightInches) {
		this(leftInches, rightInches, true);
	}

	public Position(double leftInches, double rightInches, boolean relative) {
		this.leftInches = leftInches;
		this.rightInches = rightInches;
		this.relative = relative;
	}

	public double getLeftInches() {
		return leftInches;
	}

	public double getRightInches() {
		return rightInches;
	}

	public boolean isRelative() {
		return relative;
	}

	public boolean isCloseTo(Position other, double tolerance) {
		// ideally use real distance, i'm lazy
		return (Math.abs(this.getLeftInches() - other.getLeftInches()) < tolerance
				&& Math.abs(this.getRightInches() - other.getRightInches()) < tolerance);
	}

	@Override
	public String toString() {
		return "L=" + leftInches + ",R=" + rightInches;
	}
}
