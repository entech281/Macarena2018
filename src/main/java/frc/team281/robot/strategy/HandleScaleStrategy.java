package frc.team281.robot.strategy;

import frc.team281.robot.FieldMessage;
import frc.team281.robot.WhichAutoCodeToRun;
import frc.team281.robot.FieldMessage.StartingPosition;

//handles the scale no matter what
public class HandleScaleStrategy implements AutoStrategy {

    @Override
    public WhichAutoCodeToRun getAutoPath(FieldMessage fieldMessage) {
        StartingPosition startingPosition = fieldMessage.getPosition();
        if ( startingPosition == StartingPosition.LEFT){
            if ( fieldMessage.isOurScaleOnTheLeft()){
                //go right along the left
                return WhichAutoCodeToRun.B;
            }
            else{
                //the one that goes to the opposite scale
                return WhichAutoCodeToRun.E;
            }
        }
        else if ( startingPosition== StartingPosition.MIDDLE){
            return WhichAutoCodeToRun.NONE;
        }
        else {// we are on the right 
            if ( fieldMessage.isOurScaleOnTheRight()){
                //go right along the left
                return WhichAutoCodeToRun.B_MIRRORED;
            }
            else{
                //the one that goes around the swtich to the back
                return WhichAutoCodeToRun.E_MIRRORED;
            }            
        }
    }

}
