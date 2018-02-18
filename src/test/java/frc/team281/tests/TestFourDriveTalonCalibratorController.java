package frc.team281.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.mockito.Mockito;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.team281.robot.subsystems.TalonSettings;
import frc.team281.robot.subsystems.TalonSettingsBuilder;
import frc.team281.robot.subsystems.drive.FourDriveTalonCalibratorController;
import frc.team281.robot.subsystems.drive.FourTalonsWithSettings;

public class TestFourDriveTalonCalibratorController extends BaseTest{

	@Test
	public void testCalibrationDriveController() {
		
		//not a good test yet-- just constructs it to make sure there are no problems there.
		//TODO: mock out the talons to return bad encoder readings
		
		long CALIBRATION_TIME_MS = 200;
		FourTalonsWithSettings settings = makeFakeTalonSettingsGroup();

		
		FourDriveTalonCalibratorController controller = new FourDriveTalonCalibratorController(settings,CALIBRATION_TIME_MS);
		assertFalse(controller.isCalibrationReady());
		
		assertEquals("FourDriveTalonCalibratorController",controller.getName());
		assertEquals(controller,controller);
		
		controller.initialize();
		
		
		
	}
}
