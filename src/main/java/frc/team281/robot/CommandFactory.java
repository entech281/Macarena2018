package frc.team281.robot;

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

    DriveToPositionCommand createPositionCommand();

}
