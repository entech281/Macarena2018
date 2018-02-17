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
    
    private JoystickButton positionButton;
    private JoystickButton lifterRaiseButton;
    private JoystickButton lifterLowerButton;
    private JoystickButton lifterStopButton;  
    private JoystickButton lifterScaleHighButton;
    private JoystickButton lifterScaleMidButton;
    private JoystickButton lifterScaleLowButton;
    private JoystickButton lifterFenceButton;
    private JoystickButton lifterGroundButton;  
    private JoystickButton grabberLoadButton;
    private JoystickButton grabberShootButton;
    private JoystickButton grabberStopButton;
    private JoystickButton grabberOpenButton;
    private JoystickButton grabberCloseButton;
    
    public OperatorInterface(CommandFactory factory) {
        this.factory = factory;
    }

    public void initialize() {
        driveJoystick = new Joystick(RobotMap.DriveJoystick.PORT);
        controlPanel = new Joystick(RobotMap.ControlPanel.PORT);
        
        positionButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.POSITION);
        positionButton.whenPressed(factory.createPositionCommand());
        
        // Lifter Subsystem
        lifterRaiseButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.LIFTER_RAISE);
        lifterRaiseButton.whenPressed(factory.createLifterRaiseCommand());
        lifterLowerButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.LIFTER_LOWER);
        lifterLowerButton.whenPressed(factory.createLifterLowerCommand());
        // lifterStopButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.LIFTER_STOP);
        // lifterStopButton.whenPressed(factory.createLifterStopCommand());
        lifterScaleHighButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.LIFTER_SCALE_HIGH);
        lifterScaleHighButton.whenPressed(factory.createLifterScaleHighCommand());
        lifterScaleMidButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.LIFTER_SCALE_MID);
        lifterScaleMidButton.whenPressed(factory.createLifterScaleMidCommand());
        lifterScaleLowButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.LIFTER_SCALE_LOW);
        lifterScaleLowButton.whenPressed(factory.createLifterScaleLowCommand());
        lifterFenceButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.LIFTER_SCALE_FENCE);
        lifterFenceButton.whenPressed(factory.createLifterFenceCommand());
        lifterGroundButton = new JoystickButton(driveJoystick, RobotMap.DriveJoystick.Buttons.LIFTER_SCALE_GROUND);
        lifterGroundButton.whenPressed(factory.createLifterGroundCommand());
        
        // Grabber Subsystem
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
        
        // Wrist Subsystem
        // No button for these as they should happen automatically 

    }

    @Override
    public DriveInstruction getNextInstruction() {
        return new DriveInstruction(
                driveJoystick.getY(),
                driveJoystick.getX()
                
                //adjustJoystickSoftness(RobotMap.JOYSTICK_Y_SOFTNESS, driveJoystick.getY()),
                //adjustJoystickSoftness(RobotMap.JOYSTICK_X_SOFTNESS, driveJoystick.getX())
                
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
