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

    public static final String THIS_SIDE_SELECTOR_PREFERENCE = "THIS_SIDE";
    public static final String OPPOSITE_SIDE_SELECTOR_PREFERENCE = "OPPOSITE_SIDE";
    public static final String FRONT_SLASH_SELECTOR = "FRONT_SLASH";
    public static final String BACK_SLASH_SELECTOR = "BACK_SLASH";

    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String E = "E";
    public static final String F = "F";
    
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
            .forward(18.69*12)
            .right(90)
            .forward(15.14*12)
            .right(90)
            .build();

    // CENTER
    protected List<Position> AUTO_D = PositionCalculator.builder()
            .forward(2.0*12)
            .left(45)
            .forward(6.5*12)
            .right(45)
            .build();

    // DRIVE TO CENTER POSITION
    protected List<Position> AUTO_E = PositionCalculator.builder()
            .forward(19.5*12)
            .right(90)
            .forward(7.9*12)
            .build();

    // OPPOSITE SIDE SCALE
    protected List<Position> AUTO_F = PositionCalculator.builder()
        .forward(19.5*12)
        .right(90)
        .forward(15.8*12)
        .left(90)
        .build();

    protected List<Position> EMPTY = PositionCalculator.builder().build();

    public AutoPlan computePlanFromFieldPoseSwitches(FieldMessage fm, boolean bothThisSideSelector, boolean frontSlashSelector,
            boolean backSlashSelector, boolean bothOppositeSelector){
        
        //do nothing by default
        AutoPlan selectedPlan = new AutoPlan("DoNothing",false,false,EMPTY);
        
        if ( fm.isRobotInMiddle()){
            selectedPlan =  new AutoPlan(D,false, true, AUTO_D);
            if ( fm.isOurScaleOnTheRight()){
                selectedPlan.setMirror(true);
            }
        } else {
            //we're on one side or the other
            //use the field Pose to decide what to do
            FieldPose pose = fm.getFieldPose();

            // For the moment, we don't drop the cube if we're travelling to opposite side of field
            // AutoPlan logicals: goingForSwitch, dropCube
            if ( pose == FieldPose.BOTH_OUR_SIDE){
                if ( bothThisSideSelector ) {
                    selectedPlan = new AutoPlan(B,true,true,AUTO_B);
                } else {
                    selectedPlan = new AutoPlan(A,false,true,AUTO_A);
                }
            }
            if ( pose == FieldPose.BOTH_OTHER_SIDE){
                if ( bothOppositeSelector ){
                    selectedPlan = new AutoPlan(F,true,false,AUTO_F);
                }
                else{
                    selectedPlan = new AutoPlan(C,false,false,AUTO_C);
                }
            }
            if ( pose == FieldPose.FRONT_SLASH){
                if ( frontSlashSelector ){
                    selectedPlan = new AutoPlan(F,true,false,AUTO_F);
                }
                else{
                    selectedPlan = new AutoPlan(A,false,true,AUTO_A);
                }
            }
            if ( pose == FieldPose.BACK_SLASH){
                if ( backSlashSelector ){
                    selectedPlan = new AutoPlan(B,true,true,AUTO_B);
                }
                else{
                    selectedPlan = new AutoPlan(C,false,false,AUTO_C);
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
