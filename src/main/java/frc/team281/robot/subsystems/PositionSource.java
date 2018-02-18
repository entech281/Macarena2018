package frc.team281.robot.subsystems;

public interface PositionSource {
	public Position getNextPosition();

	public boolean isFinished();
}
