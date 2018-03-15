package frc.team281.robot.strategy;

import frc.team281.robot.FieldMessage;
import frc.team281.robot.WhichAutoCodeToRun;

public interface AutoStrategy {

    //returns WhichAutoCodeToRun.NONE if the strategy cannot execute,
    //or an auto path if it can
    public WhichAutoCodeToRun getAutoPath(FieldMessage fieldMessage);
}
