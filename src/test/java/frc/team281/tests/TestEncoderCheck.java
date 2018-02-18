package frc.team281.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.team281.robot.controllers.EncoderCheck;
import frc.team281.robot.subsystems.drive.FourTalonsWithSettings;

public class TestEncoderCheck extends BaseTest{

	public static final int BIGGER_THAN_ZERO = 22;


	
	@Test
	public void testAllEncodersWorking() {
				
		EncoderCheck ec = new EncoderCheck(BIGGER_THAN_ZERO, BIGGER_THAN_ZERO, BIGGER_THAN_ZERO, BIGGER_THAN_ZERO);

		assertTrue(ec.allOk());
		assertFalse(ec.hasProblems());
		assertTrue(ec.canDrive());
		assertTrue(ec.isLeftOk());
		assertTrue(ec.isRightOk());
		assertTrue(ec.isLeftRearOk());
		assertTrue(ec.isRightRearOk());
		assertTrue(ec.isLeftFrontOk());
		assertTrue(ec.isLeftRearOk());
		assertFalse(ec.shouldLeftRearFollowLeftFront());
		assertFalse(ec.shouldRightFrontFollowRightRear());
		assertFalse(ec.shouldRightRearFollowRightFront());
		assertFalse(ec.shouldLeftFrontFollowLeftRear());
	}

	@Test
	public void testLeftRearBroken() {
		
		
		EncoderCheck ec = new EncoderCheck(0, BIGGER_THAN_ZERO, BIGGER_THAN_ZERO, BIGGER_THAN_ZERO);

		assertFalse(ec.allOk());
		assertTrue(ec.canDrive());
		assertTrue(ec.isLeftOk());
		assertFalse(ec.isLeftRearOk());
		assertTrue(ec.isLeftFrontOk());
		assertTrue(ec.shouldLeftRearFollowLeftFront());
		assertFalse(ec.shouldRightFrontFollowRightRear());
		assertFalse(ec.shouldRightRearFollowRightFront());
		assertFalse(ec.shouldLeftFrontFollowLeftRear());
	}

	@Test
	public void testRightFrontBroken() {
		
		FourTalonsWithSettings settings = makeFakeTalonSettingsGroup();
		EncoderCheck ec = new EncoderCheck(BIGGER_THAN_ZERO, BIGGER_THAN_ZERO, 0, BIGGER_THAN_ZERO);

		assertEquals(settings.getFrontRightSettings().controlMode , ControlMode.PercentOutput);
		
		assertFalse(ec.allOk());
		assertTrue(ec.canDrive());
		assertTrue(ec.isRightOk());
		assertFalse(ec.isRightFrontOk());
		assertTrue(ec.isRightRearOk());
		assertFalse(ec.shouldLeftRearFollowLeftFront());
		assertTrue(ec.shouldRightFrontFollowRightRear());
		assertFalse(ec.shouldRightRearFollowRightFront());
		assertFalse(ec.shouldLeftFrontFollowLeftRear());
				
		ec.adjustTalonSettingsToWorkAroundBrokenEncoders(settings);
		assertEquals(settings.getFrontRightSettings().controlMode , ControlMode.Follower);
		
	}

	@Test
	public void testBothLeftBroken() {
		
		FourTalonsWithSettings settings = makeFakeTalonSettingsGroup();
		
		EncoderCheck ec = new EncoderCheck(0, 0, BIGGER_THAN_ZERO, BIGGER_THAN_ZERO);

		assertFalse(ec.allOk());
		assertFalse(ec.canDrive());
		assertTrue(ec.isRightOk());
		assertFalse(ec.isLeftOk());
		assertTrue(ec.isRightRearOk());
		assertFalse(ec.isLeftFrontOk());
		assertFalse(ec.isLeftRearOk());
		assertFalse(ec.shouldLeftRearFollowLeftFront());
		assertFalse(ec.shouldRightFrontFollowRightRear());
		assertFalse(ec.shouldRightRearFollowRightFront());
		assertFalse(ec.shouldLeftFrontFollowLeftRear());
		
		ec.adjustTalonSettingsToWorkAroundBrokenEncoders(settings);
		assertEquals(settings.getFrontLeftSettings().controlMode , ControlMode.Disabled);
		assertEquals(settings.getFrontRightSettings().controlMode , ControlMode.Disabled);
		assertEquals(settings.getRearLeftSettings().controlMode , ControlMode.Disabled);
		assertEquals(settings.getRearRightSettings().controlMode , ControlMode.Disabled);
	}
}
