package frc.team281.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team281.robot.RobotMap;

/**
 * This is the real lifter system. All of the wpilib code goes here.
 * 
 * @author dcowden
 *
 */
public class RealLifterSubsystem extends BaseLifterSubsystem {

	private Solenoid upSolenoid;
	private Solenoid downSolenoid;
	private DigitalInput upperLimit;
	private DigitalInput lowerLimit;

	public RealLifterSubsystem() {
		super();
	}

	@Override
	public void initialize() {
		upperLimit = new DigitalInput(RobotMap.DigitalIO.UPPER_LIFTER_LIMIT);
		lowerLimit = new DigitalInput(RobotMap.DigitalIO.LOWER_LFTER_LIMIT);
	}

	@Override
	public void raise() {
		upSolenoid.set(true);
		downSolenoid.set(false);
		dataLogger.log("raise", true);
	}

	@Override
	public void lower() {
		upSolenoid.set(true);
		downSolenoid.set(false);
		dataLogger.log("raise", true);
	}

	@Override
	public boolean isAtTop() {
		// TODO Auto-generated method stub
		return upperLimit.get();
	}

	@Override
	public boolean isAtBottom() {
		// TODO Auto-generated method stub
		return lowerLimit.get();
	}

	@Override
	protected void initDefaultCommand() {

	}

}
