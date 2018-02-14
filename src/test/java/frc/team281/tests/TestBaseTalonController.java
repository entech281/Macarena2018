package frc.team281.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.team281.robot.controllers.TalonSpeedController;
import frc.team281.robot.subsystems.TalonSettings;
import frc.team281.robot.subsystems.TalonSettingsBuilder;

public class TestBaseTalonController {

    @Test
    public void testActualPositionREturnsNullWhenFollowing() {

        TalonSRX talon = Mockito.mock(TalonSRX.class);

        int FAKE_TALON_ID = 7;
        int PID_SLOT = 0;
        TalonSettings settings = TalonSettingsBuilder.defaults().withCurrentLimits(35, 30, 200).coastInNeutral()
                .withDirections(false, false).noMotorOutputLimits().noMotorStartupRamping().useSpeedControl().build();

        settings.configureTalon(talon);

        TalonSettings follower = TalonSettingsBuilder.follow(settings, FAKE_TALON_ID);

        Mockito.when(talon.getSelectedSensorPosition(PID_SLOT)).thenReturn(3);
        Mockito.when(talon.getControlMode()).thenReturn(ControlMode.Follower);

        TalonSpeedController controller = new TalonSpeedController(talon, follower);
        assertEquals(null, controller.getActualPosition());

    }
}
