package frc.team281.robot;

import frc.team281.robot.commands.GrabberCloseCommand;
import frc.team281.robot.commands.GrabberLoadCommand;
import frc.team281.robot.commands.GrabberOpenCommand;
import frc.team281.robot.commands.GrabberShootCommand;
import frc.team281.robot.commands.GrabberStopCommand;
import frc.team281.robot.commands.LifterHomeCommand;
import frc.team281.robot.commands.LifterLowerCommand;
import frc.team281.robot.commands.LifterRaiseCommand;
import frc.team281.robot.commands.WristPivotDownCommand;
import frc.team281.robot.commands.WristPivotUpCommand;

/**
 * An interface that creates commands. This is a seam that allows testing
 * OperatorInterface without the need to create a RObot instance, which cannot
 * be done because wpilib
 * 
 * @author dcowden
 *
 */
public interface CommandFactory {
    
    LifterRaiseCommand createLifterRaiseCommand();
    
    LifterLowerCommand createLifterLowerCommand();
    
    LifterHomeCommand createLifterHomeCommand();
    
    GrabberLoadCommand createGrabberLoadCommand();
    
    GrabberShootCommand createGrabberShootCommand();
    
    GrabberStopCommand createGrabberStopCommand();
    
    GrabberOpenCommand createGrabberOpenCommand();
    
    GrabberCloseCommand createGrabberCloseCommand();
    
    WristPivotUpCommand createWristPivotUpCommand();
    
    WristPivotDownCommand createWristPivotDownCommand();

}
