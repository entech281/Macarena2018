package frc.team281.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import frc.team281.robot.ConvertFieldMessageToCommandGroup;
import frc.team281.robot.FieldMessage;
import frc.team281.robot.FieldMessage.StartingPosition;
import frc.team281.robot.WhichAutoCodeToRun;
import frc.team281.robot.FieldMessage.Override;

public class TestConvertFieldMessageToCommandGroup {
	private ConvertFieldMessageToCommandGroup converter = new ConvertFieldMessageToCommandGroup();
	private FieldMessage message = new FieldMessage();
	
	protected void configureMessage(StartingPosition p, boolean isOurSwitchOnTheLeft, boolean isOurScaleOnTheLeft, Override o) {
		message.setOurScaleOnTheLeft(isOurScaleOnTheLeft);
		message.setOurSwitchOnTheLeft(isOurSwitchOnTheLeft);
		message.setPosition(p);
		message.setOverride(o);
	} 
	
	@Test
	public void testStartingPositionLeftSwitchRightScaleRight() {
		
		configureMessage(StartingPosition.LEFT, false, false, Override.NO);
		assertEquals(WhichAutoCodeToRun.C,converter.convert(message));
	}

	@Test
	public void testStartingPositionLeftSwitchLeftScaleRight() {
		
		configureMessage(StartingPosition.LEFT, true, false, Override.YES);		
		assertEquals(WhichAutoCodeToRun.F,converter.convert(message));
	}
	
	@Test
	public void testStartingPositionRightSwitchLeftScaleRight() {
		
		configureMessage(StartingPosition.RIGHT, true, false, Override.NO);
		assertEquals(WhichAutoCodeToRun.B1,converter.convert(message));
	}
	
	@Test
	public void testStartingPositionRightSwitchRightScaleRight() {
		
		configureMessage(StartingPosition.RIGHT, false, false, Override.YES);
		assertEquals(WhichAutoCodeToRun.B1,converter.convert(message));
	}
	
	@Test
	public void testStartingPositionMiddleSwitchRightScaleRight() {
		
		configureMessage(StartingPosition.MIDDLE, false, false, Override.NO);
		assertEquals(WhichAutoCodeToRun.D1,converter.convert(message));
	}
}


