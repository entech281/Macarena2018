package frc.team281.robot;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team281.robot.FieldMessage.Override;
import frc.team281.robot.FieldMessage.StartingPosition;





public class FieldMessageGetter {
	
	boolean leftSwitchValue = false;
	boolean rightSwitchValue = false;
	boolean preferenceSwitchValue = false;
	
	
	public FieldMessageGetter(boolean leftSwitchValue, boolean rightSwitchValue, boolean preferenceSwitchValue) {
		this.leftSwitchValue = leftSwitchValue;
		this.rightSwitchValue = rightSwitchValue;
		this.preferenceSwitchValue = preferenceSwitchValue;
	}
	
    public boolean isRobotOnTheLeft() {
        return ! leftSwitchValue; 
    }
    
    public boolean isRobotOnTheRight() {
        return ! rightSwitchValue; 
    }
    
    public boolean Override() {
        return ! preferenceSwitchValue; 
    }

	
	public FieldMessage convertGameMessageToFieldMessage(String gameMessage) {
	    SmartDashboard.putBoolean("RobotLeftSwitch", isRobotOnTheLeft());
	    SmartDashboard.putBoolean("RobotRightSwitch", isRobotOnTheRight());
	    SmartDashboard.putBoolean("Overide", ! Override());
		FieldMessage message = new FieldMessage();
		if(gameMessage.charAt(0) == 'L') {
			message.setOurSwitchOnTheLeft(true); 
		}
		else {
			message.setOurSwitchOnTheLeft(false);
		}
		if(gameMessage.charAt(1) == 'L') {
			message.setOurScaleOnTheLeft(true); 
			
		}
		else {
			message.setOurScaleOnTheLeft(false);
		}
		if(gameMessage.charAt(2) == 'L') {
			message.setTheirSwitchOnTheLeft(true); 
		}
		else {
			message.setTheirSwitchOnTheLeft(false);
		}
		
		if ( isRobotOnTheLeft()) {
			message.setPosition(StartingPosition.LEFT);
		}
		
		else if ( isRobotOnTheRight()) {
			message.setPosition(StartingPosition.RIGHT);
		}
		
		else {
			message.setPosition(StartingPosition.MIDDLE);
		}
		
		if ( Override()) {
			message.setOverride(Override.YES);
		}
		else {
			message.setOverride(Override.NO);
		}
		
		return message;
		
		
	}
	
}