package frc.team281.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

public class PositionBuffer implements PositionSource {

	private List<Position> targetList = new ArrayList<>();

	public void addPosition(Position target) {
		targetList.add(target);
	}

	@Override
	public Position getCurrentPosition() {
	    if ( targetList.isEmpty() ){
	        return null;
	    }
	    else{
	        return targetList.get(0);
	    }
		
	}

	@Override
	public boolean hasNextPosition() {
		return ! targetList.isEmpty();
	}

	@Override
	public Position next() {
		if ( hasNextPosition() ) {
			return targetList.remove(0);
		}
		else {
			return null;
		}
		
	}
}
