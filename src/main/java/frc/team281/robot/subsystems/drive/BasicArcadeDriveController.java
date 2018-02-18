package frc.team281.robot.subsystems.drive;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.team281.robot.DriveInstruction;
import frc.team281.robot.DriveInstructionSource;

/**
 * Basic speed control using built-in WPILib stuff. Nothing fancy.
 * 
 * @author dcowden
 *
 */
public class BasicArcadeDriveController extends BaseDriveController {

	private DriveInstructionSource driveInstructionSource;
	private DifferentialDrive diffDrive;
	private FourTalonsWithSettings talons;

	public BasicArcadeDriveController(FourTalonsWithSettings talons, DriveInstructionSource driveInstructionSource) {
		this.talons = talons;
		this.driveInstructionSource = driveInstructionSource;
	}

	@Override
	public void activate() {
		talons.configureAll();
		SpeedControllerGroup left = new SpeedControllerGroup(talons.getFrontLeft(), talons.getRearLeft());
		left.setInverted(true);
		
		SpeedControllerGroup right = new SpeedControllerGroup(talons.getFrontRight(), talons.getRearRight());
		right.setInverted(true);
		
		diffDrive = new DifferentialDrive(left,right);
		diffDrive.setSafetyEnabled(true);
		
	}

	@Override
	public void periodic() {
		DriveInstruction di = driveInstructionSource.getNextInstruction();
		diffDrive.arcadeDrive(di.getForward(), di.getLateral());

	}

	@Override
	public void deactivate() {
		diffDrive.setSafetyEnabled(false);		
	}

}
