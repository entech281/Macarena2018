package frc.team281.robot.subsystems.drive;

/**
 * This prevents a bunch of opportunity for null references,
 * and also gives us a chance to do things when we're idle
 * in base controller
 * @author dcowden
 *
 */
public class DoNothingDriveController extends BaseDriveController{

    @Override
    public void activate() {
    }

    @Override
    public void periodic() {
        
    }

    @Override
    public void deactivate() {
        
    }

}
