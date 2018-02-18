package frc.team281.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;;

/**
 * Reads the hardware that interfaces with real users, and issues commands to a
 * Robot.
 * 
 * Note that this class is not dependent on any Robot subsystems, or the Robot
 * itself.
 * 
 * It translates joysticks and buttons into commands and DriveInstructions
 */
public class OperatorInterface implements DriveInstructionSource {

    private Joystick driveJoystick;
    private JoystickButton positionButton;
    private CommandFactory factory;

    public OperatorInterface(CommandFactory factory) {
        this.factory = factory;
    }

    public void initialize() {
        driveJoystick = new Joystick(RobotMap.JoystickPorts.JOYSTICK_1);
        positionButton = new JoystickButton(driveJoystick, RobotMap.JoystickButtons.POSITION);
    }

    @Override
    public DriveInstruction getNextInstruction() {
        return new DriveInstruction(
                -(1.0)*driveJoystick.getX(),
                driveJoystick.getY()
                );
    }

    public static double adjustJoystickSoftness(double softnessFactor, double rawValue) {
        double adjusted = Math.pow(rawValue, softnessFactor);

        if (rawValue < 0) {
            return -adjusted;
        } else {
            return adjusted;
        }
    }
}
