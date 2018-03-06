package frc.team281.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import frc.team281.robot.subsystems.Position;

public class TestPosition {

	@Test
	public void testPositionCloseTo() {

		Position x = new Position(0, 0);
		Position y = new Position(18, 18);

		assertFalse(x.isCloseTo(y, 0.001));
		assertTrue(x.isCloseTo(y, 26.0));
	}

	@Test
	public void testGetters() {
		Position x = new Position(2.0, 3.0);
		assertEquals(2.0, x.getLeftInches(), 0.001);
		assertEquals(3.0, x.getRightInches(), 0.001);
	}
}
