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

    // OUR SIDE SWITCH
    protected List<Position> AUTO_A =  PositionCalculator.builder()
            .forward(14.*12)
            .right(90)
            .build();

    // OUR SIDE SCALE
    protected List<Position> AUTO_B = PositionCalculator.builder()
            .forward(25.*12)
            .right(90)
            .build();

    // OPPOSITE SIDE SWITCH
    protected List<Position> AUTO_C = PositionCalculator.builder()
            .forward(19.5*12)
            .right(90)
            .forward(15.8*12)
            .right(90)
            .build();

    // CENTER
    protected List<Position> AUTO_D = PositionCalculator.builder()
            .forward(2.0*12)
            .left(45)
            .forward(6.5*12)
            .right(45)
            .build();

    // DRIVE FORWARD ONLY
    protected List<Position> AUTO_E = PositionCalculator.builder()
            .forward(11.5*12)
            .build();

    // OPPOSITE SIDE SCALE
    protected List<Position> AUTO_F = PositionCalculator.builder()
        .forward(19.5*12)
        .right(90)
        .forward(15.8*12)
        .left(90)
        .build();

    protected List<Position> EMPTY = PositionCalculator.builder().build();

    public AutoPlan computePlan(FieldMessage fm, int code){
        //TODO:convert the into 4 booleans
        return computePlanFromFieldPoseSwitches(fm,false,false,false,false);
    }

    public AutoPlan testPlanE(){
        return new AutoPlan("Aryan Test",true,true,AUTO_E);
    }
    
    public AutoPlan computePlanFromFieldPoseSwitches(FieldMessage fm, boolean bothThisSideSelector, boolean frontSlashSelector, 
            boolean backSlashSelector, boolean bothOppositeSelector){
        
        //do nothing by default
        AutoPlan selectedPlan = new AutoPlan("DoNothing",false,false,EMPTY);
        
        if ( fm.isRobotInMiddle()){
            selectedPlan =  new AutoPlan("D",false, true, AUTO_D);
            if ( fm.isOurScaleOnTheRight()){
                selectedPlan.setMirror(true);
            }
        } else {
            //we're on one side or the other
            //use the field Pose to decide what to do
            FieldPose pose = fm.getFieldPose();
            
            if ( pose == FieldPose.BOTH_OUR_SIDE){
                if ( bothThisSideSelector ) {
                    selectedPlan = new AutoPlan("B",true,true,AUTO_B);
                } else {
                    selectedPlan = new AutoPlan("A",false,true,AUTO_A);
                }
            }
            if ( pose == FieldPose.BOTH_OTHER_SIDE){
                if ( bothOppositeSelector ){
                    selectedPlan = new AutoPlan("F",true,true,AUTO_F);
                }
                else{
                    selectedPlan = new AutoPlan("D",false,true,AUTO_D);
                }
            }
            if ( pose == FieldPose.FRONT_SLASH){
                if ( frontSlashSelector ){
                    selectedPlan = new AutoPlan("F",true,true,AUTO_F);
                }
                else{
                    selectedPlan = new AutoPlan("A",false,true,AUTO_A);
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
            //mirror the path if needed
            if ( fm.isRobotOnright()){
                selectedPlan.setMirror(true);
            }
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
