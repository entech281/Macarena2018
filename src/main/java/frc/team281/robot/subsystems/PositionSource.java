package frc.team281.robot.subsystems;

public interface PositionSource {
	public Position getCurrentPosition();
	public boolean hasNextPosition();
	public void next();
}
