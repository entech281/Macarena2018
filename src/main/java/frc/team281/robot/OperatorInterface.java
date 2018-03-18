package frc.team281.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team281.robot.commands.LifterStopCommand;;

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
    private JoystickButton lifterTopButton;
    private JoystickButton grabberLoadButton;
    private JoystickButton grabberShootButton;
    private JoystickButton grabberOpenButton;
    private JoystickButton wristUpButton;
    private JoystickButton bothThisSideScaleInAutoButton;
    private JoystickButton frontslashScaleInAutoButton;
    private JoystickButton backslashScaleInAutoButton;
    private JoystickButton bothOppositeScaleInAutoButton;

    
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
        
        lifterRaiseButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.LIFTER_RAISE);
        lifterRaiseButton.whileHeld(factory.createLifterRaiseCommand());
        lifterRaiseButton.whenReleased(factory.createLifterStopCommand());

        
        lifterLowerButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.LIFTER_LOWER);
        lifterLowerButton.whileHeld(factory.createLifterLowerCommand());
        lifterLowerButton.whenReleased(factory.createLifterStopCommand());

        lifterGroundButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.LIFTER_TO_GROUND);
        lifterGroundButton.whenPressed(factory.createLifterHomeCommand());

        
        lifterTopButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.LIFTER_TO_TOP);
        lifterTopButton.whenPressed(factory.createLifterTopCommand());

        wristUpButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.WRIST_UP);
        wristUpButton.whenPressed(factory.createWristPivotUpCommand());
        wristUpButton.whenReleased(factory.createWristPivotDownCommand());

        grabberLoadButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.GRABBER_LOAD);
        grabberLoadButton.whenPressed(factory.createGrabberLoadCommand());
        grabberLoadButton.whenReleased(factory.createGrabberStopCommand());
        
        grabberShootButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.GRABBER_SHOOT);
        grabberShootButton.whenPressed(factory.createGrabberShootCommand());
        grabberShootButton.whenReleased(factory.createGrabberStopCommand());
        
        grabberOpenButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.GRABBER_OPEN);
        grabberOpenButton.whenPressed(factory.createGrabberOpenCommand());
        grabberOpenButton.whenReleased(factory.createGrabberCloseCommand());

        bothThisSideScaleInAutoButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.AUTO_SCALE_LEFT);
        frontslashScaleInAutoButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.AUTO_SCALE_SLASH);
        backslashScaleInAutoButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.AUTO_SCALE_BACKSLASH);
        bothOppositeScaleInAutoButton = new JoystickButton(controlPanel, RobotMap.ControlPanel.Buttons.AUTO_SCALE_RIGHT);
    }

    @Override
    public DriveInstruction getNextInstruction() {
        double x;

        if (driveJoystick.getTrigger()) {
            // TODO: verify the sign on twist
            x = -driveJoystick.getTwist();
        } else {
            x = -driveJoystick.getX();
        }
        double y = driveJoystick.getY();

        return new DriveInstruction( x , y );
    }

    public static double adjustJoystickSoftness(double softnessFactor, double rawValue) {
        boolean isNegative=(rawValue<0);
        double adjusted = (isNegative? Math.pow(-rawValue, softnessFactor):Math.pow(rawValue, softnessFactor));
        return isNegative?-adjusted:adjusted;
    }

    public boolean getBothThisSideScaleInAuto() {
        return bothThisSideScaleInAutoButton.get();
    }

    public boolean getFrontslashScaleInAuto() {
        return frontslashScaleInAutoButton.get();
    }

    public boolean getBackslashScaleInAuto() {
        return backslashScaleInAutoButton.get();
    }

    public boolean getBothOppositeScaleInAuto() {
        return bothOppositeScaleInAutoButton.get();
    }

}
