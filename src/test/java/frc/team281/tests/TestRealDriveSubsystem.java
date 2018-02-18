package frc.team281.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


import frc.team281.robot.subsystems.drive.RealDriveSubsystem;
import frc.team281.subsystems.FakeDriveInstructionSource;

public class TestRealDriveSubsystem extends BaseTest{
	
	@Test
	public void testBasics() {
		
		
		FakeDriveInstructionSource fakeSource = new FakeDriveInstructionSource();
		RealDriveSubsystem drive = new RealDriveSubsystem(fakeSource);
		
		
		assertEquals(drive.getName(),"RealDriveSubsystem");
		
	}

}
