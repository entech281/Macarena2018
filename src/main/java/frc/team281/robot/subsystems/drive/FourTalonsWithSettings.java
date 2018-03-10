package frc.team281.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.team281.robot.subsystems.TalonSettings;
import frc.team281.robot.subsystems.TalonSettingsBuilder;

/**
 * The motors and associated settings for 4 talons
 * 
 * @author dcowden
 *
 */
public class FourTalonsWithSettings {

	protected WPI_TalonSRX frontLeft;
	protected WPI_TalonSRX frontRight;
	protected WPI_TalonSRX rearLeft;
	protected WPI_TalonSRX rearRight;
	protected TalonSettings frontLeftSettings;
	protected TalonSettings frontRightSettings;
	protected TalonSettings rearLeftSettings;
	protected TalonSettings rearRightSettings;

	
	public FourTalonsWithSettings(WPI_TalonSRX frontLeft, WPI_TalonSRX rearLeft, WPI_TalonSRX frontRight,WPI_TalonSRX rearRight,
			TalonSettings frontLeftSettings,TalonSettings rearLeftSettings, TalonSettings frontRightSettings,TalonSettings rearRightSettings) {
	    
		this.frontLeft = frontLeft;
		this.frontRight = frontRight;
		this.rearLeft = rearLeft;
		this.rearRight = rearRight;
		this.frontLeftSettings = frontLeftSettings;
        this.rearLeftSettings = rearLeftSettings;
        this.frontRightSettings = frontRightSettings;
        this.rearRightSettings = rearRightSettings;
	}	
	private FourTalonsWithSettings() {
		
	}
	public FourTalonsWithSettings copy() {
		FourTalonsWithSettings fg = new FourTalonsWithSettings();
		fg.frontLeft = this.frontLeft;
		fg.frontRight = this.frontRight;
		fg.rearLeft = this.rearLeft;
		fg.rearRight = this.rearRight;
		fg.frontLeftSettings = this.frontLeftSettings.copy();
		fg.frontRightSettings = this.frontRightSettings.copy();
		fg.rearLeftSettings = this.rearLeftSettings.copy();
		fg.rearRightSettings = this.rearRightSettings.copy();
		return fg;
	}
	
	public void applySettings(TalonSettings leftSettings, TalonSettings rightSettings) {
		this.frontLeftSettings = leftSettings;
		this.rearLeftSettings = leftSettings;
		this.frontRightSettings = rightSettings;
		this.rearRightSettings = rightSettings;		
	}
	
	public void configureAll() {
		frontLeftSettings.configureTalon(frontLeft);
		frontRightSettings.configureTalon(frontRight);
		rearLeftSettings.configureTalon(rearLeft);
		rearRightSettings.configureTalon(rearRight);
	}

	public void disableAllSettings() {
		frontLeftSettings = TalonSettingsBuilder.disabledCopy(frontLeftSettings);
		frontRightSettings = TalonSettingsBuilder.disabledCopy(frontRightSettings);
		rearLeftSettings = TalonSettingsBuilder.disabledCopy(rearLeftSettings);
		rearRightSettings = TalonSettingsBuilder.disabledCopy(rearRightSettings);
	}

	public WPI_TalonSRX getFrontLeft() {
		return frontLeft;
	}

	public WPI_TalonSRX getFrontRight() {
		return frontRight;
	}

	public WPI_TalonSRX getRearLeft() {
		return rearLeft;
	}

	public WPI_TalonSRX getRearRight() {
		return rearRight;
	}

	public TalonSettings getFrontLeftSettings() {
		return frontLeftSettings;
	}

	public TalonSettings getFrontRightSettings() {
		return frontRightSettings;
	}

	public TalonSettings getRearLeftSettings() {
		return rearLeftSettings;
	}

	public TalonSettings getRearRightSettings() {
		return rearRightSettings;
	}

	public void setFrontLeftSettings(TalonSettings frontLeftSettings) {
		this.frontLeftSettings = frontLeftSettings;
	}

	public void setFrontRightSettings(TalonSettings frontRightSettings) {
		this.frontRightSettings = frontRightSettings;
	}

	public void setRearLeftSettings(TalonSettings rearLeftSettings) {
		this.rearLeftSettings = rearLeftSettings;
	}

	public void setRearRightSettings(TalonSettings rearRightSettings) {
		this.rearRightSettings = rearRightSettings;
	}

}
