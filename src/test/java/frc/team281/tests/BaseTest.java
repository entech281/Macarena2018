package frc.team281.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.RobotTestUtils;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team281.robot.subsystems.TalonSettings;
import frc.team281.robot.subsystems.TalonSettingsBuilder;
import frc.team281.robot.subsystems.drive.FourTalonsWithSettings;

/**
 * Base class for all robot unit tests. Make sure to extend this class for yoru
 * tests, so that everything is all set up to work correctly.
 * 
 * @author dcowden
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Scheduler.class, HLUsageReporting.class, SendableBase.class })
@PowerMockIgnore("org.jacoco.agent.rt.*")
public class BaseTest {

	@Test
	public void testFrameworkSetup() {

	}

	@Before
	public void setupWpiLib() {
		RobotTestUtils.setupForTesting();
	}
	
	public FourTalonsWithSettings makeFakeTalonSettingsGroup() {
		WPI_TalonSRX frontLeft = Mockito.mock(WPI_TalonSRX.class);
		WPI_TalonSRX frontRight = Mockito.mock(WPI_TalonSRX.class);
		WPI_TalonSRX rearLeft = Mockito.mock(WPI_TalonSRX.class);
		WPI_TalonSRX rearRight = Mockito.mock(WPI_TalonSRX.class);
		
		TalonSettings leftSpeedSettingsToAdjust = TalonSettingsBuilder.defaults().withCurrentLimits(35, 30, 200).coastInNeutral()
				.withDirections(false, false).noMotorOutputLimits().noMotorStartupRamping().useSpeedControl().build();

		TalonSettings rightSpeedSettingsToAdjust = TalonSettingsBuilder.defaults().withCurrentLimits(35, 30, 200).coastInNeutral()
				.withDirections(false, false).noMotorOutputLimits().noMotorStartupRamping().useSpeedControl().build();
		
		FourTalonsWithSettings settings = new FourTalonsWithSettings(frontLeft,frontRight,rearLeft,rearRight,leftSpeedSettingsToAdjust,rightSpeedSettingsToAdjust, rightSpeedSettingsToAdjust, rightSpeedSettingsToAdjust);
		
		return settings;
	}

}
