package frc.team281.robot.strategy;

import frc.team281.robot.FieldMessage;
import frc.team281.robot.WhichAutoCodeToRun;

public class DoNothingStrategy implements AutoStrategy {

    @Override
    public WhichAutoCodeToRun getAutoPath(FieldMessage fieldMessage) {
        return WhichAutoCodeToRun.NONE;
    }

}
