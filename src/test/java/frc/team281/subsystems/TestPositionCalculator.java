package frc.team281.subsystems;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.PositionCalculator;

public class TestPositionCalculator {
    
    protected PositionCalculator calculator = new PositionCalculator();
    public static final double TOLERANCE = 0.01;
    
    @Test
    public void testTurningRigth() {
        
        Position p = PositionCalculator.turnRight(90);  
        assertEquals(-21.6, p.getLeftInches(), TOLERANCE);
        assertEquals(21.6, p.getRightInches(), TOLERANCE);
        
    }
    @Test
    public void testGoForward() {
        
        Position p = PositionCalculator.goForward(5);   
        assertEquals(5, p.getLeftInches(), TOLERANCE);
        assertEquals(5, p.getRightInches(), TOLERANCE);
        
    }
    @Test
    public void testTurningLeft() {
        
        Position p = PositionCalculator.turnLeft(90);   
        assertEquals(21.6, p.getLeftInches(), TOLERANCE);
        assertEquals(-21.6, p.getRightInches(), TOLERANCE);
        
    }
    @Test
    public void testNegativeTurningLeft() {
        Position p = PositionCalculator.turnLeft(-90);  
        assertEquals(-21.6, p.getLeftInches(), TOLERANCE);
        assertEquals(21.6, p.getRightInches(), TOLERANCE);
        
    }
    @Test
    public void testNegativeTurningRight() {
        Position p = PositionCalculator.turnRight(-90); 
        assertEquals(21.6, p.getLeftInches(), TOLERANCE);
        assertEquals(-21.6, p.getRightInches(), TOLERANCE);
        
    }
    
    @Test
    public void testBuildingListOfPositions() {
    	List<Position> positions = PositionCalculator.builder()
    			.forward(20)
    			.left(30)
    			.forward(100)
    			.right(10)
    			.build();
    	
    	assertEquals(4, positions.size());
    }
}