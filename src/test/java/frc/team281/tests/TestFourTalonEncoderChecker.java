package frc.team281.tests;

import org.junit.Test;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.team281.robot.subsystems.drive.FourTalonsWithSettings;
import frc.team281.robot.RobotMap;
import frc.team281.robot.controllers.FourTalonEncoderChecker;
import static org.mockito.Mockito.*;

public class TestFourTalonEncoderChecker extends BaseTest{

	
	public static final int BIGGER_THAN_ZERO = 22;
	
	@Test
	public void testAllOk() {
		FourTalonsWithSettings talons = makeFakeTalonSettingsGroup();
		when(talons.getFrontLeft().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		when(talons.getFrontRight().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		when(talons.getRearLeft().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		when(talons.getRearRight().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		
		
		new FourTalonEncoderChecker(talons).setMotorsWithBrokenEncodersToFollowers();
				
		verify(talons.getFrontLeft(), never()).set(anyObject(), anyDouble() );
		verify(talons.getFrontRight(), never()).set(anyObject(), anyDouble() );
		verify(talons.getRearLeft(), never()).set(anyObject(), anyDouble() );
		verify(talons.getRearRight(), never()).set(anyObject(), anyDouble() );

	}
	
	@Test
	public void testLeftRearBroken() {
		FourTalonsWithSettings talons = makeFakeTalonSettingsGroup();
		when(talons.getFrontLeft().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		when(talons.getFrontRight().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		when(talons.getRearLeft().getSelectedSensorPosition(0)).thenReturn(0);
		when(talons.getRearRight().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		
		
		new FourTalonEncoderChecker(talons).setMotorsWithBrokenEncodersToFollowers();
				
		verify(talons.getFrontLeft(), never()).set(anyObject(), anyDouble() );
		verify(talons.getFrontRight(), never()).set(anyObject(), anyDouble() );
		verify(talons.getRearLeft()).set(ControlMode.Follower, RobotMap.CAN.FRONT_LEFT_MOTOR);
		verify(talons.getRearRight(), never()).set(anyObject(), anyDouble() );				
	}
	
	@Test
	public void testBothLeftBroken() {
		FourTalonsWithSettings talons = makeFakeTalonSettingsGroup();
		when(talons.getFrontLeft().getSelectedSensorPosition(0)).thenReturn(0);
		when(talons.getFrontRight().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		when(talons.getRearLeft().getSelectedSensorPosition(0)).thenReturn(0);
		when(talons.getRearRight().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		
		
		new FourTalonEncoderChecker(talons).setMotorsWithBrokenEncodersToFollowers();
				
		
		verify(talons.getFrontLeft()).set(ControlMode.Disabled, 0);
		verify(talons.getFrontRight()).set(ControlMode.Disabled, 0);
		verify(talons.getRearLeft()).set(ControlMode.Disabled, 0);
		verify(talons.getRearRight()).set(ControlMode.Disabled, 0);
	
	}
}
