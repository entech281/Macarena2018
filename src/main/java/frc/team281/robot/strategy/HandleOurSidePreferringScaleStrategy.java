package frc.team281.robot.strategy;

import frc.team281.robot.FieldMessage;
import frc.team281.robot.WhichAutoCodeToRun;
import frc.team281.robot.FieldMessage.StartingPosition;

/**
 * tries to handle our side. 
 *     if only one thing is available, we'll do that one
 *     if both are available, we'll prefer the scale
 *     if neither are available, we'll run auto G
 * @author dcowden
 *
 */
public class HandleOurSidePreferringScaleStrategy implements AutoStrategy {

    @Override
    public WhichAutoCodeToRun getAutoPath(FieldMessage fieldMessage) {
        StartingPosition startingPosition = fieldMessage.getPosition();

        if ( startingPosition == StartingPosition.LEFT){
            if ( fieldMessage.isOurScaleOnTheLeft()){
                return WhichAutoCodeToRun.B;
            }
            
            if ( fieldMessage.isOurSwitchOnTheLeft()){
                //go right along the left
                return WhichAutoCodeToRun.A;
            }

        }
        if ( startingPosition == StartingPosition.RIGHT){
            if ( fieldMessage.isOurScaleOnTheRight()){
                return WhichAutoCodeToRun.B_MIRRORED;
            }  
            
            if ( fieldMessage.isOurSwitchOnTheRight()){
                //go right along the left
                return WhichAutoCodeToRun.A_MIRRORED;
            }
        
        }
        return WhichAutoCodeToRun.G;    
    }

}
