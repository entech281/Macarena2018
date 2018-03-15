package frc.team281.robot.strategy;

import frc.team281.robot.FieldMessage;
import frc.team281.robot.FieldMessage.StartingPosition;
import frc.team281.robot.WhichAutoCodeToRun;

/**
 * A strategy that handles the switch no matter what.
 * @author dcowden
 *
 */
public class HandleSwitchStrategy implements AutoStrategy{

    @Override
    public WhichAutoCodeToRun getAutoPath(FieldMessage fieldMessage) {
        StartingPosition startingPosition = fieldMessage.getPosition();
        if ( startingPosition == StartingPosition.LEFT){
            if ( fieldMessage.isOurSwitchOnTheLeft()){
                //go right along the left
                return WhichAutoCodeToRun.A;
            }
            else{
                //the one that goes around the swtich to the back
                return WhichAutoCodeToRun.C;
            }
        }
        else if ( startingPosition== StartingPosition.MIDDLE){
            if ( fieldMessage.isOurSwitchOnTheLeft()){
                return WhichAutoCodeToRun.D;
            }
            else{
                return WhichAutoCodeToRun.D_MIRRORED;
            }
        }
        else {// we are on the right 
            if ( fieldMessage.isOurSwitchOnTheRight()){
                //go right along the left
                return WhichAutoCodeToRun.A_MIRRORED;
            }
            else{
                //the one that goes around the swtich to the back
                return WhichAutoCodeToRun.C_MIRRORED;
            }            
        }
    }

}
