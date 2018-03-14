package frc.team281.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import frc.team281.robot.controllers.TalonPositionController;
import frc.team281.robot.controllers.TalonPositionControllerGroup;

public class TestTalonControllerGroup {

	protected TalonPositionController lf = Mockito.mock(TalonPositionController.class);
	protected TalonPositionController lr = Mockito.mock(TalonPositionController.class);
	protected TalonPositionController rf = Mockito.mock(TalonPositionController.class);
	protected TalonPositionController rr = Mockito.mock(TalonPositionController.class);

	protected TalonPositionControllerGroup group;

	@Before
	public void setup() {
		group = new TalonPositionControllerGroup(lf, rf, lr, rr);
	}

	@Test
	public void testReset() {
		group.resetMode();

		Mockito.verify(lf).resetMode();
		Mockito.verify(lr).resetMode();
		Mockito.verify(rf).resetMode();
		Mockito.verify(rr).resetMode();
	}

	@Test
	public void testEncoderCountLogicWithOneBroken() {

		// simulate lr returning null as if its a follower
		Mockito.when(rr.getActualPosition()).thenReturn(100);
		Mockito.when(rf.getActualPosition()).thenReturn(200);
		Mockito.when(lf.getActualPosition()).thenReturn(300);
		Mockito.when(lr.getActualPosition()).thenReturn(null);

		// should be average of right raer and right front
		assertEquals(150, group.computeRightEncoderCounts());

		// should be just left front, since left rear is broken
		assertEquals(300, group.computeLeftEncoderCounts());
	}

	@Test
	public void testEncoderCountLogicWithAllWorking() {

		// simulate lr returning null as if its a follower
		Mockito.when(rr.getActualPosition()).thenReturn(100);
		Mockito.when(rf.getActualPosition()).thenReturn(200);
		Mockito.when(lf.getActualPosition()).thenReturn(300);
		Mockito.when(lr.getActualPosition()).thenReturn(500);

		// should be average of right raer and right front
		assertEquals(150, group.computeRightEncoderCounts());

		// should be just left front, since left rear is broken
		assertEquals(400, group.computeLeftEncoderCounts());
	}

	/*@Test
	 public void testSetDesiredPosition() {
		// left, right

		group.setDesiredPosition(100, 200, true);

		Mockito.verify(lf).setDesiredPosition(100);
		Mockito.verify(lr).setDesiredPosition(100);
		Mockito.verify(rf).setDesiredPosition(200);
		Mockito.verify(rr).setDesiredPosition(200);
	}*/
}
