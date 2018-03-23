package frc.team281.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.team281.robot.RobotMap;
import frc.team281.robot.controllers.SettableEncoder;
import frc.team281.robot.controllers.TalonSpeedController;

public class LifterSubsystem extends BaseSubsystem {

    private WPI_TalonSRX motorOne;
    private WPI_TalonSRX motorTwo;
    private TalonSpeedController motorOneController;
    private TalonSpeedController motorTwoController;

    public static final double CURRENT_STOP_FACTOR = 1.15;
    public static final double UP_SPEED_PERCENT = 0.7;
    public static final double DOWN_SPEED_PERCENT = 0.55;
    public static final double SLOW_SPEED_UP_PERCENT = 0.4;
    public static final double SLOW_SPEED_DOWN_PERCENT = 0.35;
    public static final int MIN_HEIGHT_COUNTS = 0;
    public static final int MAX_HEIGHT_COUNTS = 4268;
    public static final int RAMP_DOWN_COUNTS = 300;

    private DigitalInput bottomLimitSwitch;
    private DigitalInput topLimitSwitch;
    private SettableEncoder encoder;
    private boolean movingUp = false;
    private boolean movingDown = false;
    
    private double upMotorCurrentBaseline;
    private boolean truelyAtTop = false; 
    
    public LifterSubsystem() {
        super();
    }

    @Override
    public void initialize() {
        motorOne = new WPI_TalonSRX(RobotMap.CAN.Lifter.MOTOR_ONE);
        motorTwo = new WPI_TalonSRX(RobotMap.CAN.Lifter.MOTOR_TWO);
        bottomLimitSwitch = new DigitalInput(RobotMap.DigitalIO.LIFTER_AT_BOTTOM);
        topLimitSwitch = new DigitalInput(RobotMap.DigitalIO.LIFTER_AT_TOP);
        encoder = new SettableEncoder( new Encoder(RobotMap.DigitalIO.LIFTER_ENCODER_A, RobotMap.DigitalIO.LIFTER_ENCODER_B) );
        encoder.reset();
        TalonSettings motorSettings = TalonSettingsBuilder.defaults()
                .withCurrentLimits(20, 15, 200)
                .brakeInNeutral()
                .withDirections(false, false)
                .noMotorOutputLimits()
                .noMotorStartupRamping()
                .useSpeedControl()
                .build();
     
        
        //TalonSettings motorTwoSettings = TalonSettingsBuilder.inverted(motorOneSettings);
        motorOneController = new TalonSpeedController(motorOne, motorSettings);
        motorTwoController = new TalonSpeedController(motorTwo, motorSettings);
        
        motorOneController.configure();
        motorTwoController.configure();
    }

    public void motorsUp(double speedPercent) {
        if ( ! isLifterAtTop() ){
            
            motorOneController.setDesiredSpeed(speedPercent);
            motorTwoController.setDesiredSpeed(speedPercent);
            movingUp = true;
        }
        else{
            dataLogger.warn("Cannot Move-- at Limits");
            motorsOff();
        }
    }

    public void motorsDown(double speedPercent) {
        if ( ! isLifterAtBottom() ){
            
            motorOneController.setDesiredSpeed(-speedPercent);
            motorTwoController.setDesiredSpeed(-speedPercent);
            movingDown = true;
            truelyAtTop = isTopLimitSwitchPressed();
        }
        else{
            dataLogger.warn("Cannot Move-- at Limits");
            motorsOff();
        }
    }

    public void motorsOff() {
        motorOneController.setDesiredSpeed(0);
        motorTwoController.setDesiredSpeed(0);
        movingDown = false;
        movingUp = false;
    }

    @Override
    public void periodic() {
    	int currentPosition = encoder.get();
        dataLogger.log("UpperLimit",isLifterAtTop());
        dataLogger.log("UpperLimitSwitch",isTopLimitSwitchPressed());
        dataLogger.log("LowerLimit",isLifterAtBottom());
        dataLogger.log("lifter Position 1: ",motorOneController.getActualPosition());
        dataLogger.log("lifter Position 2: ",motorTwoController.getActualPosition());
        dataLogger.log("EncoderCount:" ,currentPosition);
        
        if (movingUp && ( ! isTopLimitSwitchPressed() )) {
        	    upMotorCurrentBaseline = getAverageMotorCurrent();
        }
        if ( movingUp ) {
        	if ( isLifterAtTop()) {
        	  encoder.set(MAX_HEIGHT_COUNTS);
        	  motorsOff();
        	}
        	else if ( currentPosition > ( MAX_HEIGHT_COUNTS - RAMP_DOWN_COUNTS ) ) {
        		motorsUp(SLOW_SPEED_UP_PERCENT);
        	}
        }
        if ( movingDown) {
        	if ( isLifterAtBottom()) {        
        	    motorsOff();
        	    encoder.reset();
        	}
        	else if ( currentPosition < RAMP_DOWN_COUNTS) {
        		motorsUp(SLOW_SPEED_DOWN_PERCENT);
        	}
        }
    }
    
    public boolean isLifterAtBottom() {
        return ! bottomLimitSwitch.get();
    }

    public boolean isLifterAtTop() {
        // return ! topLimitSwitch.get();
    	if (movingUp && isTopLimitSwitchPressed() &&
    			(getAverageMotorCurrent() > CURRENT_STOP_FACTOR*upMotorCurrentBaseline)) {
    		truelyAtTop = true;
    	}
    	return truelyAtTop;
    }
    
    private boolean isTopLimitSwitchPressed() {
        return ! topLimitSwitch.get();
    }
    private double getAverageMotorCurrent() {
    	return 0.5*(motorOne.getOutputCurrent() + 
    			    motorTwo.getOutputCurrent()   );
    }
}
