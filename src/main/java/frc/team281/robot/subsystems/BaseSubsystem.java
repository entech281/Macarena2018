package frc.team281.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team281.robot.logger.DataLogger;
import frc.team281.robot.logger.DataLoggerFactory;

/**
 * Base class for all subsystems. Each subsystem should provide 3 classes based
 * off of this one: BaseYourSubsystem should subclass this class, and provide
 * stuff common to both real and test versions RealYourSubsystem should extend
 * BaseYourSubsystem, and should be the real implemetnation. It should be in
 * src/main, so it is distributed to the robot TestYourSubsystem should also
 * extend BaseYourSubsystem, and should be in src/test. It will be used for
 * tests, but not distributed to the robot.
 * 
 * @author dcowden
 *
 */
public abstract class BaseSubsystem extends Subsystem {

	protected DataLogger dataLogger;

	public BaseSubsystem() {
		this.dataLogger = DataLoggerFactory.getLoggerFactory().createDataLogger(this.getName());
		dataLogger.log(this);
	}

	public abstract void initialize();

	@Override
	protected void initDefaultCommand() {

	}

}
