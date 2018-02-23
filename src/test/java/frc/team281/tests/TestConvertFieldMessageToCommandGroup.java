package frc.team281.tests;

import org.junit.Test;

import frc.team281.robot.ConvertFieldMessageToCommandGroup;
import frc.team281.robot.FieldMessage;
import frc.team281.robot.FieldMessage.StartingPosition;
import frc.team281.robot.ConvertFieldMessageToCommandGroup.WhichAutoCodeToRun;

import static org.junit.Assert.assertEquals;

public class TestConvertFieldMessageToCommandGroup {
	private ConvertFieldMessageToCommandGroup converter = new ConvertFieldMessageToCommandGroup();
	private FieldMessage message = new FieldMessage();
	
	protected void configureMessage(StartingPosition p, boolean isOurSwitchOnTheLeft, boolean isOurScaleOnTheLeft) {
		message.setOurScaleOnTheLeft(isOurScaleOnTheLeft);
		message.setOurSwitchOnTheLeft(isOurSwitchOnTheLeft);
		message.setPosition(p);
	} 
	
	@Test
	public void testStartingPositionLeftSwitchRightScaleRight() {
		
		configureMessage(StartingPosition.LEFT, false, false);
		assertEquals(WhichAutoCodeToRun.C,converter.convert(message));
	}

	@Test
	public void testStartingPositionLeftSwitchLeftScaleRight() {
		
		configureMessage(StartingPosition.LEFT, true, false);		
		assertEquals(WhichAutoCodeToRun.B,converter.convert(message));
	}
	
	@Test
	public void testStartingPositionRightSwitchLeftScaleRight() {
		
		configureMessage(StartingPosition.RIGHT, true, false);
		assertEquals(WhichAutoCodeToRun.D1,converter.convert(message));
	}
	
	@Test
	public void testStartingPositionRightSwitchRightScaleRight() {
		
		configureMessage(StartingPosition.RIGHT, false, false);
		assertEquals(WhichAutoCodeToRun.A1,converter.convert(message));
	}
	
	@Test
	public void testStartingPositionMiddleSwitchRightScaleRight() {
		
		configureMessage(StartingPosition.MIDDLE, false, false);
		assertEquals(WhichAutoCodeToRun.E,converter.convert(message));
	}
}


