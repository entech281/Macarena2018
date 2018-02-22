package frc.team281.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team281.robot.FieldMessage.StartingPosition;
import frc.team281.robot.FieldMessageGetter;


public class ConvertFieldMessageToCommandGroup {
	
	public enum WhichAutoCodeToRun{
		A,
		B,
		C,
		D,
		A1,
		B1,
		C1,
		D1,
		E,
		F;
	}
	
	public WhichAutoCodeToRun convert(FieldMessage message) {
		 
		if(message.getPosition()==StartingPosition.LEFT) {
			if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == true) {
				 return WhichAutoCodeToRun.A;			
				}
			else if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == false) {
				return WhichAutoCodeToRun.B;			
				}
			else if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == false) {
				return WhichAutoCodeToRun.C;			
				}
			else if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == true) {
				return WhichAutoCodeToRun.D;			
				}
		}
	
		else if(message.getPosition()==StartingPosition.RIGHT) {
			if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == false) {
				return WhichAutoCodeToRun.A1;			
				}
			else if(message.isOurSwitchOnTheLeft() == false && message.isOurScaleOnTheLeft() == true) {
				return WhichAutoCodeToRun.B1;			
				}
			else if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == true) {
				return WhichAutoCodeToRun.C1;			
				}
			else if(message.isOurSwitchOnTheLeft() == true && message.isOurScaleOnTheLeft() == false) {
				return WhichAutoCodeToRun.D1;			
				}
			}	
			else if(message.getPosition()==StartingPosition.MIDDLE){
				if(message.isOurSwitchOnTheLeft() == false) {
					return WhichAutoCodeToRun.E;
				}
				else {
					return WhichAutoCodeToRun.F;
				}
			}
		return null;
		
	}
}