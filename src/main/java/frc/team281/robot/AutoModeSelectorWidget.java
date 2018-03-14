package frc.team281.robot;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team281.robot.FieldMessage.StartingPosition;


/**
 * Uses values onthe smart dashboard to select auto mode
 * @author dave
 *
 */
public class AutoModeSelectorWidget extends SendableBase{

	
	public static final String AUTO_SELECTOR_WIDGET = "AutoModeSelectorWidget";
	
	private double leftSwitchPriority = 1;
	private double leftScalePriority = 2;
	private double rightSwitchPriority = 3;
	private double rightScalePriority = 4;
	
	public static final String LSW = "LSW";
	public static final String LSC = "LSC";
	public static final String RSW = "RSW";
	public static final String RSC = "RSC";
	
	private String [] priority = new String[] { LSW, LSC, RSW, RSC};
	
	public String[] getPriority() {
		return priority;
	}

	public void setPriority(String[] priority) {
		this.priority = priority;
	}

	public void setDefaultPriorities() {
		leftSwitchPriority = 1;
		leftScalePriority = 2;
		rightSwitchPriority = 3;
		rightScalePriority = 4;		
		priority = new String[] { LSW, LSC, RSW, RSC};
	}
	
	public double getLeftSwitchPriority() {
		return leftSwitchPriority;
	}

	public void setLeftSwitchPriority(double leftSwitchPriority) {
		this.leftSwitchPriority = leftSwitchPriority;
	}

	public double getLeftScalePriority() {
		return leftScalePriority;
	}

	public void setLeftScalePriority(double leftScalePriority) {
		this.leftScalePriority = leftScalePriority;
	}

	public double getRightSwitchPriority() {
		return rightSwitchPriority;
	}

	public void setRightSwitchPriority(double rightSwitchPriority) {
		this.rightSwitchPriority = rightSwitchPriority;
	}

	public double getRightScalePriority() {
		return rightScalePriority;
	}

	public void setRightScalePriority(double rightScalePriority) {
		this.rightScalePriority = rightScalePriority;
	}


	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("Team281Auto");
		builder.setSafeState(this::setDefaultPriorities);
		builder.addDoubleProperty("LeftSwitch", this::getLeftSwitchPriority, this::setLeftSwitchPriority);
		builder.addDoubleProperty("RightSwitch", this::getRightSwitchPriority, this::setRightSwitchPriority);
		builder.addDoubleProperty("LeftScale", this::getLeftScalePriority, this::setLeftScalePriority);
		builder.addDoubleProperty("RightScale", this::getRightScalePriority, this::setRightScalePriority);
		builder.addStringArrayProperty("Priorities", this::getPriority, this::setPriority);
	}

	//given a position, can we do this auto?
	//returns null if we can't do it.
	public WhichAutoCodeToRun isEligibleFor(String option, StartingPosition p ) {
		if ( p == StartingPosition.LEFT) {
			if ( option.equals(LSW)) return WhichAutoCodeToRun.B;
			if ( option.equals(LSC)) return WhichAutoCodeToRun.A;
			return null;

		}
		else if (p == StartingPosition.MIDDLE) {
			if ( option.equals(LSW)) return WhichAutoCodeToRun.D;
			if ( option.equals(RSW)) return WhichAutoCodeToRun.D_MIRRORED;
			return null;						
		}
		else {
			if ( option.equals(RSW)) return WhichAutoCodeToRun.B_MIRRORED;
			if ( option.equals(RSC)) return WhichAutoCodeToRun.A_MIRRORED;
			return null;						
		}		
	}
	
	public WhichAutoCodeToRun selectAuto(FieldMessage fieldMessage) {
		StartingPosition p = fieldMessage.getPosition();
		for ( String option: getPriority() ) {
			WhichAutoCodeToRun w = isEligibleFor(option,p);
			if ( w != null ) return w;
		}
		return WhichAutoCodeToRun.NONE;

	}
}
