package frc.team281.robot.subsystems;

import java.util.ArrayList;
import java.util.List;


public class PositionCalculator {
    
    public static final double DISTANCE_BETWEEN_WHEELS = 27.5;
    
    private static double computeTurn(double degrees) {
        return (((DISTANCE_BETWEEN_WHEELS*Math.PI)/360)*degrees);
        
    }
    
    public static Position goForward(double inches) {
        double desiredPosition = inches;        
        return new Position(desiredPosition, desiredPosition);
    }
    
    public static Position turnRight(double degrees) {
    	double d = computeTurn(degrees);
    	return new Position( d,-d);
    }
    
    
    public static Position turnLeft(double degrees) {
    	double d = computeTurn(degrees);
    	return new Position( -d,d);
    }
    
    public static BasicMoves builder() {
    	return new Builder();
    }
    
    public interface BasicMoves{
    	BasicMoves right(double degrees);
    	BasicMoves left(double degrees);
    	BasicMoves forward(double inches);
    	BasicMoves backward(double inches);
    	BasicMoves mirror();
    	List<Position> build();
    }

    public static class Builder implements BasicMoves{
    	private List<Position> commands = new ArrayList<>();

		@Override
		public List<Position> build() {
			return commands;
		}

		@Override
		public BasicMoves right(double degrees) {
			commands.add(turnRight(degrees));
			return this;
		}

		@Override
		public BasicMoves left(double degrees) {
			commands.add(turnLeft(degrees));
			return this;
		}

		@Override
		public BasicMoves forward(double inches) {
			commands.add(goForward(inches));
			return this;
		}

		@Override
		public BasicMoves backward(double inches) {
			commands.add(goForward(-inches));
			return this;
		}

        @Override
        public BasicMoves mirror() {
            BasicMoves b = new Builder();
            for(int i=0;i<commands.size();i++) {
                if(commands.get(i).getLeftInches()>0 && commands.get(i).getRightInches()>0) {
                    b.forward(commands.get(i).getLeftInches());
                }else if(commands.get(i).getLeftInches()<0 && commands.get(i).getRightInches()<0) {
                    b.backward(commands.get(i).getLeftInches());
                }else if(commands.get(i).getLeftInches()<0 && commands.get(i).getRightInches()>0) {
                    b.left(commands.get(i).getLeftInches());
                }else {
                    b.right(commands.get(i).getLeftInches());
                }
            }
            return b;
        }
    	    	
    }	
	
    
}