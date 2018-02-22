package frc.team281.tests;

import org.junit.Test;

import frc.team281.robot.ConvertFieldMessageToCommandGroup;
import frc.team281.robot.FieldMessage;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team281.robot.FieldMessage.StartingPosition;
import frc.team281.robot.FieldMessageGetter;
import frc.team281.robot.ConvertFieldMessageToCommandGroup.WhichAutoCodeToRun;

import static org.junit.Assert.assertEquals;

public class TestConvertFieldMessageToCommandGroup {
	private ConvertFieldMessageToCommandGroup converter = new ConvertFieldMessageToCommandGroup();
	
	@Test
	public void testRobotStartingPositionAndSwitchAndScaleOnOurSide(FieldMessage message) {
		
		message.setPosition(StartingPosition.LEFT);
		message.setOurScaleOnTheLeft(true);
		message.setOurSwitchOnTheLeft(true);
		
		assertEquals(WhichAutoCodeToRun.A,converter);
	}

	@Test
	public void testRobotStartingPositionAndSwitchAndScaleOnOur(FieldMessage message) {
		
		message.setPosition(StartingPosition.LEFT);
		message.setOurScaleOnTheLeft(true);
		message.setOurSwitchOnTheLeft(true);
		
		assertEquals(WhichAutoCodeToRun.A,converter);
	}
}


