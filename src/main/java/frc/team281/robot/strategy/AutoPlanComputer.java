package frc.team281.robot.strategy;

import edu.wpi.first.wpilibj.Preferences;
import frc.team281.robot.FieldMessage;

/**
 * Class for selecting strategies
 * @author dcowden
 *
 */
public class AutoPlanComputer {

    public static final String PREFERENCE_STRING = "AutoStrategySelector";
    
    //TODO: have to implement this to get all of the right plans from the code
    //this is quite a lot of logical code
    public AutoPlan computePlan(FieldMessage fm, int code){
        return null;
    }
    
    public AutoPlan computePlanFromButtons(FieldMessage fm, boolean buttonOne, boolean buttonTwo, boolean buttonThree){
        int code = getCodeFromButtons( buttonOne, buttonTwo, buttonThree);
        return computePlan(fm,code);
    }
    
    public AutoPlan selectPlanFromRobotPreferences(FieldMessage fm){
        int code = Preferences.getInstance().getInt(PREFERENCE_STRING, -1);
        return computePlan(fm,code);
    }
    
   
    public int getCodeFromButtons( boolean buttonOne, boolean buttonTwo, boolean buttonThree){
        int total = 0;
        if ( buttonOne){
            total += 1;
        }
        if ( buttonTwo){
            total += 2;
        }
        if ( buttonThree){
            total += 4;
        }
        return total;
    }
    
    
    
}
