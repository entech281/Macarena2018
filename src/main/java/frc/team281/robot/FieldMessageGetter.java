package frc.team281.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team281.robot.FieldMessage.StartingPosition;


public class FieldMessageGetter {
	
	boolean leftSwitchValue = false;
	boolean rightSwitchValue = false;
	boolean overrideSwitchValue = false;
	public FieldMessageGetter(boolean leftSwitchValue, boolean rightSwitchValue, boolean overrideSwitchValue) {
		this.leftSwitchValue = leftSwitchValue;
		this.rightSwitchValue = rightSwitchValue;
		this.overrideSwitchValue = overrideSwitchValue;
	}
	
    public boolean isRobotOnTheLeft() {
        return ! leftSwitchValue; 
    }
    
    public boolean isRobotOnTheRight() {
        return ! rightSwitchValue; 
    }

	
	public FieldMessage convertGameMessageToFieldMessage(String gameMessage) {
	    SmartDashboard.putBoolean("RobotLeftSwitch", isRobotOnTheLeft());
	    SmartDashboard.putBoolean("RobotRightSwitch", isRobotOnTheRight());
	    SmartDashboard.putBoolean("RobotRightSwitch", ! overrideSwitchValue);
	    
		FieldMessage message = new FieldMessage();
		message.setOverrideSwitch(! overrideSwitchValue);
		
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
		
		return message;
		
		
	}
	
}