package frc.team281.robot.strategy;

import java.util.List;

import edu.wpi.first.wpilibj.Preferences;
import frc.team281.robot.FieldMessage;
import frc.team281.robot.strategy.FieldPose;
import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.PositionCalculator;

/**
 * Class for selecting strategies
 * @author dcowden
 *
 */
public class AutoPlanComputer {

    public static final String THIS_SIDE_SELECTOR_PREFERENCE = "THIS_SIDE_SELECTOR";
    public static final String OPPOSITE_SIDE_SELECTOR_PREFERENCE = "OPPOSITE_SIDE_SELECTOR_PREFERENCE";
    public static final String FRONT_SLASH_SELECTOR = "FRONT_SLASH_SELECTOR";
    public static final String BACK_SLASH_SELECTOR = "BACK_SLASH_SELECTOR";
    
    protected List<Position> AUTO_A =  PositionCalculator.builder()
            .forward(14*12)
            .right(90)
            .build();

    protected List<Position> AUTO_B = PositionCalculator.builder() 
            .forward(25*12)
            .right(90)
            .build();    
    
    protected List<Position> AUTO_C = PositionCalculator.builder()
            .forward(235)
            .right(90)
            .forward(190)
            .right(90)
            .forward(10)
            .build();
    
    protected List<Position> AUTO_D = PositionCalculator.builder()
            .forward(24)
            .left(45)
            .forward(78)
            .right(45)
            //.forward(48)
            .build();
    
    protected List<Position> AUTO_E = PositionCalculator.builder()
            .forward(138)
            .build();
    
    protected List<Position> AUTO_F = PositionCalculator.builder()
            .forward(138)
            .left(25)     
            .forward(111)     
            .right(35)        
            .forward(84)      
            .right(45)        
            .forward(52)      
            .right(45)        
            .forward(130)     
            .left(90)     
            .forward(41)
            .build();
    
    protected List<Position> EMPTY = PositionCalculator.builder().build();
     
    
    public AutoPlan computePlan(FieldMessage fm, int code){
        //TODO:convert the into 4 booleans
        return computePlanFromFieldPoseSwitches(fm,false,false,false,false);
    }
    
    public AutoPlan computePlanFromFieldPoseSwitches(FieldMessage fm, boolean bothThisSideSelector, boolean frontSlashSelector, 
            boolean backSlashSelector, boolean bothOppositeSelector){
        
        //do nothing by default
        AutoPlan selectedPlan = new AutoPlan("DoNothing",false,false,EMPTY);
        
        if ( fm.isRobotInMiddle()){
            if ( fm.isOurScaleOnTheLeft()){
                selectedPlan =  new AutoPlan("D",false, true, AUTO_D);
            }
        }
        else{
            //we're on one side or the other
            //use the field Pose to decide what to do
            FieldPose pose = fm.getFieldPose();
            
            if ( pose == FieldPose.BOTH_OUR_SIDE){
                if ( bothThisSideSelector ){
                    selectedPlan = new AutoPlan("B",true,true,AUTO_B);
                }
                else{
                    selectedPlan = new AutoPlan("A",false,true,AUTO_A);
                }
            }
            if ( pose == FieldPose.BOTH_OTHER_SIDE){
                if ( bothOppositeSelector ){
                    selectedPlan = new AutoPlan("E",true,true,AUTO_E);
                }
                else{
                    selectedPlan = new AutoPlan("D",false,true,AUTO_D);
                }
            }
            if ( pose == FieldPose.FRONT_SLASH){
                if ( frontSlashSelector ){
                    selectedPlan = new AutoPlan("A",true,true,AUTO_A);
                }
                else{
                    selectedPlan = new AutoPlan("E",false,true,AUTO_E);
                }
            }    
            if ( pose == FieldPose.BACK_SLASH){
                if ( backSlashSelector ){
                    selectedPlan = new AutoPlan("B",false,true,AUTO_B);
                }
                else{
                    selectedPlan = new AutoPlan("D",true,true,AUTO_D);
                }
            }             
        }
        
        //mirror the path if needed
        if ( fm.isRobotOnright()){
            selectedPlan.setMirror(true);
        }
        return selectedPlan;
    }
        
    public AutoPlan computePlanFromRobotPreferences(FieldMessage fm){
        Preferences p = Preferences.getInstance();
        
        return computePlanFromFieldPoseSwitches(fm,
                p.getBoolean(THIS_SIDE_SELECTOR_PREFERENCE,false),
                p.getBoolean(FRONT_SLASH_SELECTOR,false),
                p.getBoolean(BACK_SLASH_SELECTOR,false),
                p.getBoolean(OPPOSITE_SIDE_SELECTOR_PREFERENCE,false)                
         );
    }
    
}
