package frc.team281.robot;

public class FieldMessage {
	public enum StartingPosition{
		RIGHT,
		LEFT,
		MIDDLE;
	}
	
	public enum Override{
		YES,
		NO;
	}
	
	public boolean isOurSwitchOnTheLeft() {
		return ourSwitchOnTheLeft;
	}
	public void setOurSwitchOnTheLeft(boolean ourSwitchOnTheLeft) {
		this.ourSwitchOnTheLeft = ourSwitchOnTheLeft;
	}
	public boolean isOurScaleOnTheLeft() {
		return ourScaleOnTheLeft;
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
	public StartingPosition getPosition() {
		return position;
	}
	public void setPosition(StartingPosition position) {
		this.position = position;
	}
		
	private Override override = Override.NO;
	public Override getOverride() {
		return override;
	}
	public void setOverride(Override override) {
		this.override = override;	
	}
}