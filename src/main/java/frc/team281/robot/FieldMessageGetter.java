package frc.team281.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.team281.robot.FieldMessage.StartingPosition;
import frc.team281.robot.RobotMap.DigitalIO;




public class FieldMessageGetter {
	
	private DigitalInput leftPositionSwitch;
	private DigitalInput rightPositionSwitch;
	
	public FieldMessageGetter() {
		leftPositionSwitch = new DigitalInput(DigitalIO.LEFT_SWITCH_POSITION);
		rightPositionSwitch = new DigitalInput(DigitalIO.RIGHT_SWITCH_POSITION);
	}
	
    public boolean isRobotOnTheLeft() {
        return leftPositionSwitch.get();
    }
    
    public boolean isRobotOnTheRight() {
        return rightPositionSwitch.get();
    }
	
	public FieldMessage convertGameMessageToFieldMessage(String gameMessage) {
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
		
		return message;
		
		
	}
	
}