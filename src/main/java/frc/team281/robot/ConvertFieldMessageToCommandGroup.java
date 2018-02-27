package frc.team281.robot;


import frc.team281.robot.FieldMessage.Override;
import frc.team281.robot.FieldMessage.StartingPosition;


public class ConvertFieldMessageToCommandGroup {
	
	
	public WhichAutoCodeToRun convert(FieldMessage message) {
		
		WhichAutoCodeToRun convert = WhichAutoCodeToRun.A;
	
	if(message.getOverride()==Override.NO) {	
		if(message.getPosition()==StartingPosition.LEFT) {
			if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == true) {
				 return WhichAutoCodeToRun.A;			
				}
			else if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == false) {
				convert = WhichAutoCodeToRun.A;
				return WhichAutoCodeToRun.A;			
				}
			else if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == false) {
				convert = WhichAutoCodeToRun.C;
				return WhichAutoCodeToRun.C;			
				}
			else if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == true) {
				convert = WhichAutoCodeToRun.B;
				return WhichAutoCodeToRun.B;			
				}
		}
	
		else if(message.getPosition()==StartingPosition.RIGHT) {
			if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == false) {
				convert = WhichAutoCodeToRun.A1;
				return WhichAutoCodeToRun.A1;
				}
			else if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == true) {
				convert = WhichAutoCodeToRun.A1;
				return WhichAutoCodeToRun.A1;			
				}
			else if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == true) {
				convert = WhichAutoCodeToRun.C1;
				return WhichAutoCodeToRun.C1;			
				}
			else if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == false) {
				convert = WhichAutoCodeToRun.B1;
				return WhichAutoCodeToRun.B1;			
				}
			}	
			else if(message.getPosition()==StartingPosition.MIDDLE){
				if(message.isOurSwitchOnTheLeft() == false) {
					convert = WhichAutoCodeToRun.D1;
					return WhichAutoCodeToRun.D1;
				}
				else {
					convert = WhichAutoCodeToRun.D;
					return WhichAutoCodeToRun.D;
				}
			}
		
	}
	
	if(message.getOverride()==Override.YES) {	
		if(message.getPosition()==StartingPosition.LEFT) {
			if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == true) {
				convert = WhichAutoCodeToRun.B;
				 return WhichAutoCodeToRun.B;			
				}
			else if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == false) {
				convert = WhichAutoCodeToRun.F;
				return WhichAutoCodeToRun.F;			
				}
			else if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == false) {
				convert = WhichAutoCodeToRun.F;
				return WhichAutoCodeToRun.F;			
				}
			else if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == true) {
				convert = WhichAutoCodeToRun.B;
				return WhichAutoCodeToRun.B;			
				}
		}
	
		else if(message.getPosition()==StartingPosition.RIGHT) {
			if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == false) {
				convert = WhichAutoCodeToRun.B1;
				return WhichAutoCodeToRun.B1;
				}
			else if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == true) {
				convert = WhichAutoCodeToRun.F1;
				return WhichAutoCodeToRun.F1;			
				}
			else if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == true) {
				convert = WhichAutoCodeToRun.F1;
				return WhichAutoCodeToRun.F1;			
				}
			else if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == false) {
				convert = WhichAutoCodeToRun.B1;
				return WhichAutoCodeToRun.B1;			
				}
			}	
			else if(message.getPosition()==StartingPosition.MIDDLE){
				if(message.isOurSwitchOnTheLeft() == false) {
					convert = WhichAutoCodeToRun.E1;
					return WhichAutoCodeToRun.E1;
				}
				else {
					convert = WhichAutoCodeToRun.E;
					return WhichAutoCodeToRun.E;

				}
			}
		
	}
	return convert;
}
}	