package frc.team281.robot;

import frc.team281.robot.commands.JoystickDriveCommand;
import frc.team281.robot.commands.LifterLowerCommand;
import frc.team281.robot.commands.LifterRaiseCommand;
import frc.team281.robot.commands.DriveToPositionCommand;

/**
 * An interface that creates commands. This is a seam that allows testing
 * OperatorInterface without the need to create a RObot instance, which cannot
 * be done because wpilib
 * 
 * @author dcowden
 *
 */
public interface CommandFactory {

    JoystickDriveCommand createDriveCommand();

    DriveToPositionCommand createPositionCommand();
    
    // Lifter Subsystem commands
    LifterRaiseCommand createLifterRaiseCommand();
    
    LifterLowerCommand createLifterLowerCommand();
    
    // LifterStopCommand createLifterStopCommand();
    
    LifterScaleHighCommand createLifterScaleHighCommand();
    
    LifterScaleMidCommand createLifterScaleMidCommand();
    
    LifterScaleLowCommand createLifterScaleLowCommand();
    
    LifterFenceCommand createLifterFenceCommand();
    
    LifterGroundCommand createLifterGroundCommand();
    
    // Grabber Subsystem commands
    GrabberLoadCommand createGrabberLoadCommand();
    
    GrabberShootCommand createGrabberShootCommand();
    
    GrabberStopCommand createGrabberStopCommand();
    
    GrabberOpenCommand createGrabberOpenCommand();
    
    GrabberCloseCommand createGrabberCloseCommand();
    
    // Wrist Subsystem commands
    WristPivotUpCommand createWristPivotUpCommand();
    
    WristPivotDownCommand createWristPivotDownCommand();
}
