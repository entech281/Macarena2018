package frc.team281.robot.subsystems;

import frc.team281.robot.subsystems.Position;


public class PositionCalculator {
    
    public static final double DISTANCE_BETWEEN_WHEELS = 27.5;
    
    public Position goForward(double inches) {
        double desiredPosition = inches;
        
        return new Position(desiredPosition, desiredPosition);
    }
    
    public Position turnRight(double degrees) {
        double desiredPosition = (((DISTANCE_BETWEEN_WHEELS*Math.PI)/360)*degrees);
        
        return new Position(-desiredPosition, desiredPosition);
    }
    
    
    public Position turnLeft(double degrees) {
        double desiredPosition = (((DISTANCE_BETWEEN_WHEELS*Math.PI)/360)*degrees);
        
        return new Position(desiredPosition, -desiredPosition);
    }
    
}