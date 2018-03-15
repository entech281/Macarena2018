package frc.team281.robot.strategy;

import edu.wpi.first.wpilibj.Preferences;
import frc.team281.robot.FieldMessage;

/**
 * Class for selecting strategies
 * @author dcowden
 *
 */
public class AutoStrategySelector {

    public static final String PREFERENCE_STRING = "AutoStrategySelector";
    
    public AutoStrategy selectStrategyFromCode(FieldMessage fm, int code){
        if ( fm.isRobotInMiddle()){
            return new HandleSwitchStrategy();
        }
        else{
            switch ( code){
            case 0:
                return new HandleScaleStrategy();
            case 1:
                return new HandleSwitchStrategy();
            case 2:
                return new HandleOurSidePreferringScaleStrategy();
            case 3:
                return new HandleOurSidePreferringSwitchStrategy();
            default:
                //we should never get here
                return new DoNothingStrategy();
            }                    
        }
    }
    
    public AutoStrategy selectStrategyFromButtons(FieldMessage fm, boolean buttonOne, boolean buttonTwo, boolean buttonThree){
        int code = getCodeFromButtons( buttonOne, buttonTwo, buttonThree);
        return selectStrategyFromCode(fm,code);
    }
    
    public AutoStrategy selectStrategyFromRobotPreferences(FieldMessage fm){
        int code = Preferences.getInstance().getInt(PREFERENCE_STRING, -1);
        if ( code == -1){
            return new DoNothingStrategy();
        }
        else{
            return selectStrategyFromCode(fm,code);
        }
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
    
    public  AutoStrategy handleSwitch(){
        return new HandleSwitchStrategy();
    }
    public AutoStrategy handleScale(){
        return new HandleScaleStrategy();
    }
    
    
}
