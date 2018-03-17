package frc.team281.robot;

import frc.team281.robot.strategy.FieldPose;

public class FieldMessage {
	public enum StartingPosition{
		RIGHT,
		LEFT,
		MIDDLE;
	}
	
    public FieldPose getFieldPose(){
        if ( isScaleOnOurSide() && isSwitchOnOurSide()){
            return FieldPose.BOTH_OUR_SIDE;
        }
        else if ( isScaleOnOtherSide() && isSwitchOnOtherSide()){
            return  FieldPose.BOTH_OTHER_SIDE;
        }
        else if ( isScaleOnOtherSide() && isSwitchOnOurSide() ){
            return FieldPose.FRONT_SLASH;
        }
        else{
            return FieldPose.BACK_SLASH;
        }
    }
    public boolean isRobotOnLeft(){
        return position == StartingPosition.LEFT;
    }
    public boolean isRobotOnright(){
        return position == StartingPosition.RIGHT;
    }
	public boolean isRobotInMiddle(){
	    return position == StartingPosition.MIDDLE;
	}
	public boolean isRobotOnASide(){
	    return position != StartingPosition.MIDDLE;
	}
	public boolean isSwitchOnOtherSide(){
	    return ! isSwitchOnOurSide();
	}
	public boolean isScaleOnOtherSide(){
	    return ! isScaleOnOurSide();
	}
	public boolean isSwitchOnOurSide(){
	    return ( isOurSwitchOnTheLeft() && position == StartingPosition.LEFT ) ||
	           ( ! isOurSwitchOnTheLeft() && position == StartingPosition.RIGHT );  
	}
    public boolean isScaleOnOurSide(){
        return ( isOurScaleOnTheLeft() && position == StartingPosition.LEFT ) ||
               ( ! isOurScaleOnTheLeft() && position == StartingPosition.RIGHT );  
    }	
	public boolean isOurSwitchOnTheLeft() {
		return ourSwitchOnTheLeft;
	}
	public boolean isOurSwitchOnTheRight(){
	    return ! ourSwitchOnTheLeft;
	}
	public void setOurSwitchOnTheLeft(boolean ourSwitchOnTheLeft) {
		this.ourSwitchOnTheLeft = ourSwitchOnTheLeft;
	}
	public boolean isOurScaleOnTheLeft() {
		return ourScaleOnTheLeft;
	}
	public boolean isOurScaleOnTheRight(){
	    return ! ourScaleOnTheLeft;
	}
	
	public void setOurScaleOnTheLeft(boolean ourScaleOnTheLeft) {
		this.ourScaleOnTheLeft = ourScaleOnTheLeft;
	}
	public boolean isTheirSwitchOnTheLeft() {
		return theirSwitchOnTheLeft;
	}
	public void setTheirSwitchOnTheLeft(boolean theirSwitchOnTheLeft) {
		this.theirSwitchOnTheLeft = theirSwitchOnTheLeft;
	}
	
	private boolean ourSwitchOnTheLeft;
	private boolean ourScaleOnTheLeft;
	private boolean theirSwitchOnTheLeft;
	
	private StartingPosition position = StartingPosition.MIDDLE;
	private boolean overrideSwitch = false;
	public boolean isOverrideSwitch() {
        return overrideSwitch;
    }
    public void setOverrideSwitch(boolean overrideSwitch) {
        this.overrideSwitch = overrideSwitch;
    }
    public StartingPosition getPosition() {
		return position;
	}
	public void setPosition(StartingPosition position) {
		this.position = position;
	}

}