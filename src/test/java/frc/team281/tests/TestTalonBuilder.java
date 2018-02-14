package frc.team281.tests;

import org.junit.Test;
import org.mockito.Mockito;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.team281.robot.subsystems.TalonSettings;
import frc.team281.robot.subsystems.TalonSettingsBuilder;

public class TestTalonBuilder {

    @Test
    public void testMakingSpeedController() {

        // have to mock this-- creating one needs native libraries we dont have loaded.
        TalonSRX talon = Mockito.mock(TalonSRX.class);

        TalonSettings settings = TalonSettingsBuilder.defaults().withCurrentLimits(35, 30, 200).coastInNeutral()
                .withDirections(false, false).noMotorOutputLimits().noMotorStartupRamping().useSpeedControl().build();

        settings.configureTalon(talon);

    }

    @Test
    public void testMakingPositionController() {
        TalonSettings settings = TalonSettingsBuilder.defaults().withPrettySafeCurrentLimits().coastInNeutral()
                .defaultDirectionSettings().limitMotorOutputs(0.5, 0.01).noMotorStartupRamping().usePositionControl()
                .withGains(1, 2, 3, 4).withMotionProfile(3200, 43200).build();

    }

    @Test
    public void testMakingAFollower() {
        // make the first settings
        int OTHER_ID_TO_FOLLOW = 2;
        TalonSettings toFollow = TalonSettingsBuilder.defaults().withPrettySafeCurrentLimits().coastInNeutral()
                .defaultDirectionSettings().limitMotorOutputs(0.5, 0.01).noMotorStartupRamping().usePositionControl()
                .withGains(1, 2, 3, 4).withMotionProfile(3200, 43200).build();

        TalonSettings followerSettings = TalonSettingsBuilder.follow(toFollow, OTHER_ID_TO_FOLLOW);

        // TODO: assertions here
    }

    @Test
    public void testMakingAnInvertedCopyLeftVsRight() {
        TalonSettings rightSide = TalonSettingsBuilder.defaults().withPrettySafeCurrentLimits().coastInNeutral()
                .defaultDirectionSettings().limitMotorOutputs(0.5, 0.01).noMotorStartupRamping().usePositionControl()
                .withGains(1, 2, 3, 4).withMotionProfile(3200, 43200).build();

        TalonSettings leftSide = TalonSettingsBuilder.inverted(rightSide, false, true);

    }
}
