package frc.team281.robot.subsystems;

import frc.team281.robot.logger.DataLogger;
import frc.team281.robot.logger.DataLoggerFactory;

/**
 * Represents the states the drive system goes through.
 * 
 * enter* Methods attempt to change modes to the desired mode. ENTERED means the
 * state is newly acuired CURRENT means you were already in the requested state
 * REJECTED means you cant enter the state from the one you are currently in
 * 
 * @author dcowden
 *
 */
public class DriveSystemMode {

    private static DataLogger log = DataLoggerFactory.getLoggerFactory().createDataLogger("DriveSystemMode");

    private boolean calibrated = false;

    public enum DriveMode {
        DISABLED, CALIBRATING, READY, SPEED, POSITION
    }

    public enum StateResult {
        ENTERED, CURRENT, REJECTED
    }

    private DriveMode mode = DriveMode.READY;

    public DriveMode getMode() {
        return mode;
    }

    public DriveSystemMode() {

    }

    public StateResult enterCalibrate() {
        if (isDisabled()) {
            mode = DriveMode.CALIBRATING;
            return StateResult.ENTERED;
        }
        log.warn("Attempt to Calibrate when in mode:" + mode);
        return StateResult.REJECTED;
    }

    public StateResult finishCalibrating() {
        this.calibrated = true;
        mode = DriveMode.READY;
        return StateResult.ENTERED;
    }

    public StateResult enterSpeed() {
        if (isSpeedMode()) {
            return StateResult.CURRENT;
        }
        if (isDisabled() || isCalibrating()) {
            log.warn("Attempt to enter speed mode when in mode:" + mode);
            return StateResult.REJECTED;
        } else {
            mode = DriveMode.SPEED;
            return StateResult.ENTERED;
        }
    }

    public StateResult enterPosition() {
        if (isPositionMode()) {
            return StateResult.CURRENT;
        }
        if (isDisabled() || isCalibrating()) {
            log.warn("Attempt to enter position mode when in mode:" + mode);
            return StateResult.REJECTED;
        } else if (!calibrated) {
            log.warn("Cannot use position mode until calibrated");
            return StateResult.REJECTED;
        } else {
            mode = DriveMode.POSITION;
            return StateResult.ENTERED;
        }
    }

    public boolean isCalibrated() {
        return calibrated;
    }

    public boolean isSpeedMode() {
        return mode == DriveMode.SPEED;
    }

    public boolean isPositionMode() {
        return mode == DriveMode.POSITION;
    }

    public boolean isCalibrating() {
        return mode == DriveMode.CALIBRATING;
    }

    public boolean isDisabled() {
        return mode == DriveMode.DISABLED;

    }
}
