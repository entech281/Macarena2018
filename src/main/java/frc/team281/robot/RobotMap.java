package frc.team281.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * Note use of inner classes. Subscoping is much more powerful and expressive
 * than concatentation
 */
public class RobotMap {

    public static class CAN {
        public static final int FRONT_LEFT_MOTOR = 0;
        public static final int FRONT_RIGHT_MOTOR = 2;
        public static final int REAR_LEFT_MOTOR = 1;
        public static final int REAR_RIGHT_MOTOR = 3;

        public static final int PC_MODULE = 10;

    }

    public static class JoystickPorts {
        public static final int JOYSTICK_1 = 0;
    }

    public static class JoystickButtons {
        public static final int POSITION = 7;

    }

    public static class PCMChannel {

    }

    public static class DigitalIO {
        public static final int UPPER_LIFTER_LIMIT = 0;
        public static final int LOWER_LFTER_LIMIT = 1;

    }

    public static final double JOYSTICK_Y_SOFTNESS = 1.5;
    public static final double JOYSTICK_X_SOFTNESS = 1.5;

}
