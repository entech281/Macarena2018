package frc.team281.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * Note use of inner classes. Subscoping is much more powerful and expressive
 * than concatenation
 */
public class RobotMap {

    public static class CAN {
        public static final int FRONT_LEFT_MOTOR = 1;
        public static final int FRONT_RIGHT_MOTOR = 2;
        public static final int REAR_LEFT_MOTOR = 4;
        public static final int REAR_RIGHT_MOTOR = 3;
        
        public static class Lifter {
            public static final int MOTOR_ONE = 5;
            public static final int MOTOR_TWO = 6;  
        }
        
        public static class Grabber {
            public static final int MOTOR_LEFT = 8;
            public static final int MOTOR_RIGHT = 7;
        }
        
        public static final int PC_MODULE = 10;

    }

    public static class DriveJoystick {
        public static final int PORT = 0;
        public static class Buttons {   
            public static final int LIFTER_RAISE = 5;
            public static final int LIFTER_LOWER = 3;
            public static final int LIFTER_SCALE_HIGH = 8;
            public static final int LIFTER_SCALE_MID = 9;
            public static final int LIFTER_SCALE_LOW = 10;
            public static final int LIFTER_SCALE_FENCE = 11;
            public static final int LIFTER_SCALE_GROUND = 12;
        }
    }
    
    public static class ControlPanel {
        public static final int PORT = 1;
        public static class Buttons {
            public static final int LIFTER_RAISE = 9;
            public static final int LIFTER_LOWER =10;
            public static final int LIFTER_STOP = 11;
            public static final int GRABBER_LOAD = 7;
            public static final int GRABBER_SHOOT = 8;
            public static final int GRABBER_STOP = 9;
            public static final int GRABBER_OPEN = 10;
            public static final int GRABBER_CLOSE = 11;
        }
    }

    public static class PCMChannel {

    }

    public static class DigitalIO {
        public static final int LIFTER_AT_BOTTOM = 0;
        public static final int LIFTER_AT_TOP = 5;//TODO 
        public static final int GRABBER_CUBE_LOADED = 1;
        public static final int WRIST_AT_BOTTOM = 2;
        public static final int LEFT_SWITCH_POSITION = 3;
        public static final int RIGHT_SWITCH_POSITION = 4;
    }
    
    public static class PCM {
        public static class Grabber {
            public static final int OUTSIDE = 1;
            public static final int INSIDE = 3;
        }
        
        public static class Wrist {
            public static final int UP = 5;
            public static final int DOWN = 6;
        }
    }
    
    public static final double JOYSTICK_Y_SOFTNESS = 1.5;
    public static final double JOYSTICK_X_SOFTNESS = 1.5;

}
