package frc.team281.tests.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import frc.team281.robot.FieldMessage;
import frc.team281.robot.FieldMessage.StartingPosition;
import frc.team281.robot.strategy.AutoPlanComputer;
import frc.team281.robot.strategy.AutoPlan;
public class TestAutoComputer {

    protected AutoPlanComputer computer = new AutoPlanComputer();
    
    protected FieldMessage getBothLeftSideFieldPose(){
        FieldMessage fm = new FieldMessage();
        fm.setOurScaleOnTheLeft(true);
        fm.setOurSwitchOnTheLeft(true);
        fm.setPosition(StartingPosition.LEFT);
        return fm;
    }
    
    protected FieldMessage getBothRightSideFieldPose(){
        FieldMessage fm = new FieldMessage();
        fm.setOurScaleOnTheLeft(false);
        fm.setOurSwitchOnTheLeft(false);
        fm.setPosition(StartingPosition.LEFT);
        return fm;
    }
    
    protected FieldMessage getFrontSlashFieldPose(){
        FieldMessage fm = new FieldMessage();
        fm.setOurScaleOnTheLeft(false);
        fm.setOurSwitchOnTheLeft(true);
        fm.setPosition(StartingPosition.LEFT);
        return fm;        
    }
    protected FieldMessage getBackSlashFieldPose(){
        FieldMessage fm = new FieldMessage();
        fm.setOurScaleOnTheLeft(true);
        fm.setOurSwitchOnTheLeft(false);
        fm.setPosition(StartingPosition.LEFT);
        return fm;        
    }
    
    @Test
    public void testBothLeftSideFalseChoice(){
        
        FieldMessage fm = getBothLeftSideFieldPose();
        
        AutoPlan ap = computer.computePlanFromFieldPoseSwitches(fm, false, false, false, false);
        
        assertEquals(false,ap.isTargetingScale());
        assertEquals(false,ap.shouldMirror());
        assertEquals(AutoPlanComputer.A,ap.getName());               
    }
    
    @Test
    public void testBothLeftSideTrueChoiceWithRobotOnRight(){
        
        FieldMessage fm = getBothLeftSideFieldPose();
        fm.setPosition(StartingPosition.RIGHT);
        
        AutoPlan ap = computer.computePlanFromFieldPoseSwitches(fm, true, true, true, true);
        
        assertEquals(true,ap.isTargetingScale());
        assertEquals(true,ap.shouldMirror());
        assertEquals(AutoPlanComputer.F,ap.getName());               
    }    
    @Test
    public void testBothLeftSideTrueChoice(){
        
        FieldMessage fm = getBothLeftSideFieldPose();
        
        AutoPlan ap = computer.computePlanFromFieldPoseSwitches(fm, true, true, true, true);
        
        assertEquals(true,ap.isTargetingScale());
        assertEquals(false,ap.shouldMirror());
        assertEquals(AutoPlanComputer.B,ap.getName());               
    }    
    
    @Test
    public void testBothRightSideTrueChoiceWithRobotOnLeft(){        
        FieldMessage fm = getBothRightSideFieldPose();
        
        AutoPlan ap = computer.computePlanFromFieldPoseSwitches(fm, true, true, true, true);
        
        assertEquals(true,ap.isTargetingScale());
        assertEquals(false,ap.shouldMirror());
        assertEquals(AutoPlanComputer.F,ap.getName());               
    }      
    @Test
    public void testBothRightSideFalseChoiceWithRobotOnLeft(){
        FieldMessage fm = getBothRightSideFieldPose();
        
        AutoPlan ap = computer.computePlanFromFieldPoseSwitches(fm, false, false, false, false);
        
        assertEquals(false,ap.isTargetingScale());
        assertEquals(false,ap.shouldMirror());
        assertEquals(AutoPlanComputer.C,ap.getName());               
    }    
    
    
    @Test
    public void testFrontSlashFalseChoice(){
        FieldMessage fm = getFrontSlashFieldPose();
        
        AutoPlan ap = computer.computePlanFromFieldPoseSwitches(fm, false, false, false, false);
        
        assertEquals(false,ap.isTargetingScale());
        assertEquals(false,ap.shouldMirror());
        assertEquals(AutoPlanComputer.A,ap.getName());               
    }   
    
    @Test
    public void testFrontSlashTrueChoiceRobotOnLeft(){
       
        FieldMessage fm = getFrontSlashFieldPose();
        
        AutoPlan ap = computer.computePlanFromFieldPoseSwitches(fm, true, true, true, true);
        
        assertEquals(true,ap.isTargetingScale());
        assertEquals(false,ap.shouldMirror());
        assertEquals(AutoPlanComputer.F,ap.getName());               
    }    
    
    @Test
    public void testBackSlashFalseChoiceWithRobotOnLeft(){
        FieldMessage fm = getBackSlashFieldPose();
        
        AutoPlan ap = computer.computePlanFromFieldPoseSwitches(fm, false, false, false, false);
        
        assertEquals(false,ap.isTargetingScale());
        assertEquals(false,ap.shouldMirror());
        assertEquals(AutoPlanComputer.C,ap.getName());               
    }   
    
    @Test
    public void testBackSlashTrueChoiceWithRobotOnLeft(){
       
        FieldMessage fm = getBackSlashFieldPose();
        
        AutoPlan ap = computer.computePlanFromFieldPoseSwitches(fm, true, true, true, true);
        
        assertEquals(true,ap.isTargetingScale());
        assertEquals(false,ap.shouldMirror());
        assertEquals(AutoPlanComputer.B,ap.getName());               
    }     
}
