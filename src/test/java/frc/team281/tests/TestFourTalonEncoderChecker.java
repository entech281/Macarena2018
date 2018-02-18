package frc.team281.tests;

import org.junit.Test;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.team281.robot.subsystems.drive.FourTalonsWithSettings;
import frc.team281.robot.RobotMap;
import frc.team281.robot.controllers.FourTalonEncoderChecker;
import static org.mockito.Mockito.*;

public class TestFourTalonEncoderChecker extends BaseTest{

	
	public static final int BIGGER_THAN_ZERO = 50;
	public static final int TOO_SMALL_TO_TRIGGER = 10;
	
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
	public void testRightFrontBroken() {
		FourTalonsWithSettings talons = makeFakeTalonSettingsGroup();
		when(talons.getFrontLeft().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		when(talons.getFrontRight().getSelectedSensorPosition(0)).thenReturn(0);
		when(talons.getRearLeft().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		when(talons.getRearRight().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		
		
		new FourTalonEncoderChecker(talons).setMotorsWithBrokenEncodersToFollowers();
				
		verify(talons.getFrontLeft(), never()).set(anyObject(), anyDouble() );
		verify(talons.getFrontRight()).set(ControlMode.Follower, RobotMap.CAN.REAR_RIGHT_MOTOR);
		verify(talons.getRearLeft(), never()).set(anyObject(), anyDouble() );
		verify(talons.getRearRight(), never()).set(anyObject(), anyDouble() );				
	}	
	
	@Test
	public void testSmallDifferenceBetweenDoesntTrigger() {
		FourTalonsWithSettings talons = makeFakeTalonSettingsGroup();
		when(talons.getFrontLeft().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		when(talons.getFrontRight().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		when(talons.getRearLeft().getSelectedSensorPosition(0)).thenReturn(TOO_SMALL_TO_TRIGGER);
		when(talons.getRearRight().getSelectedSensorPosition(0)).thenReturn(BIGGER_THAN_ZERO);
		
		
		new FourTalonEncoderChecker(talons).setMotorsWithBrokenEncodersToFollowers();
				
		verify(talons.getFrontLeft(), never()).set(anyObject(), anyDouble() );
		verify(talons.getFrontRight(), never()).set(anyObject(), anyDouble() );
		verify(talons.getRearLeft(), never()).set(anyObject(), anyDouble() );
		verify(talons.getRearRight(), never()).set(anyObject(), anyDouble() );
	
	}
}
