package frc.team281.robot.subsystems;

/**
 * Common base class for the lifter subsystem. This contains stuff that's the
 * same for both real operation and for testing. abstract methods should be
 * implemented for real robot use, and for testing.
 * 
 * Note that this class still doesnt use any wpilib stuff!
 * 
 * @author dcowden
 */
public abstract class BaseLifterSubsystem extends BaseSubsystem {

	public static final double LIFTER_TIMEOUT = 2.0;

	// these should be implemted using wpilib for the real system,
	// and using fake data for the test system
	public abstract void raise();

	public abstract void lower();

	public abstract boolean isAtTop();

	public abstract boolean isAtBottom();

}
