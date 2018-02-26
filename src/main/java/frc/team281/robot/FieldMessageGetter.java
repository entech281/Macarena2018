package frc.team281.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team281.robot.FieldMessage.Override;
import frc.team281.robot.FieldMessage.StartingPosition;
import frc.team281.robot.RobotMap.DigitalIO;




public class FieldMessageGetter {
	
	private DigitalInput leftPositionSwitch;
	private DigitalInput rightPositionSwitch;
	private DigitalInput preferenceSwitch;
	
	public FieldMessageGetter() {
		leftPositionSwitch = new DigitalInput(DigitalIO.LEFT_SWITCH_POSITION);
		rightPositionSwitch = new DigitalInput(DigitalIO.RIGHT_SWITCH_POSITION);
		preferenceSwitch = new DigitalInput(DigitalIO.PREFERENCE_SWITCH);
	}
	
    public boolean isRobotOnTheLeft() {
        return ! leftPositionSwitch.get();
    }
    
    public boolean isRobotOnTheRight() {
        return ! rightPositionSwitch.get();
    }
    
    public boolean Override() {
        return ! preferenceSwitch.get();
    }

	
	public FieldMessage convertGameMessageToFieldMessage(String gameMessage) {
	    SmartDashboard.putBoolean("RobotLeftSwitch", leftPositionSwitch.get());
	    SmartDashboard.putBoolean("RobotRightSwitch", rightPositionSwitch.get());
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