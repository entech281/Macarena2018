package frc.team281.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.PositionBuffer;

public class TestPositionBuffer {

	@Test
	public void testEmptyBufferReadsFinished() {
		PositionBuffer buf = new PositionBuffer();
		assertFalse(buf.hasNextPosition());
		
		buf.addPosition(new Position(80,80));
		assertTrue(buf.hasNextPosition());
		
		Position p = buf.getCurrentPosition();
		assertTrue(buf.hasNextPosition());
		
		buf.next();
		assertFalse(buf.hasNextPosition());
	}
	
}
