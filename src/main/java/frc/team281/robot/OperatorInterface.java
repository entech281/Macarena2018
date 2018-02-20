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
    private Joystick controlPanel;
    
    private CommandFactory factory;
    
    private JoystickButton lifterRaiseButton;
    private JoystickButton lifterLowerButton; 
    private JoystickButton lifterGroundButton;  
    private JoystickButton grabberLoadButton;
    private JoystickButton grabberShootButton;
    private JoystickButton grabberStopButton;
    private JoystickButton grabberOpenButton;
    private JoystickButton grabberCloseButton;
    
    public static class LifterHeights {
        public static final double SCALE_HIGH = 100;
        public static final double SCALE_MID = 80;
        public static final double SCALE_LOW = 60;
        public static final double FENCE = 40;
        public static final double GROUND = 20;
    }
    
    public OperatorInterface(CommandFactory factory) {
        this.factory = factory;
    }

    public void initialize() {

        driveJoystick = new Joystick(RobotMap.DriveJoystick.PORT);
        controlPanel = new Joystick(RobotMap.ControlPanel.PORT);
        
        lifterRaiseButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.LIFTER_RAISE);
        lifterRaiseButton.whenPressed(factory.createLifterRaiseCommand());
        lifterLowerButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.LIFTER_LOWER);
        lifterLowerButton.whenPressed(factory.createLifterLowerCommand());

        lifterGroundButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.LIFTER_SCALE_GROUND);
        lifterGroundButton.whenPressed(factory.createLifterHomeCommand());
        
        grabberLoadButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.GRABBER_LOAD);
        grabberLoadButton.whenPressed(factory.createGrabberLoadCommand());
        grabberShootButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.GRABBER_SHOOT);
        grabberShootButton.whenPressed(factory.createGrabberShootCommand());
        grabberStopButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.GRABBER_STOP);
        grabberStopButton.whenPressed(factory.createGrabberStopCommand());
        grabberOpenButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.GRABBER_OPEN);
        grabberOpenButton.whenPressed(factory.createGrabberOpenCommand());
        grabberCloseButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.GRABBER_CLOSE);
        grabberCloseButton.whenPressed(factory.createGrabberCloseCommand()); 

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
