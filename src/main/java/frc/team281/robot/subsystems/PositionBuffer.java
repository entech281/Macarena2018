package frc.team281.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

public class PositionBuffer implements PositionSource {

	private List<Position> targetList = new ArrayList<>();

	public void addPosition(Position target) {
		targetList.add(target);
	}

	@Override
	public Position getNextPosition() {
		return targetList.remove(0);
	}

	@Override
	public boolean isFinished() {
		return targetList.size() == 0;

	}

}
