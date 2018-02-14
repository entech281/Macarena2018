package frc.team281.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team281.robot.logger.DataLogger;
import frc.team281.robot.logger.DataLoggerFactory;
import frc.team281.robot.subsystems.BaseSubsystem;

/**
 * All commands must subclass this command. We require that all commands have a
 * subsystem, and a timeout
 * 
 * @author dcowden
 *
 */
public abstract class BaseCommand extends Command {

    protected DataLogger dataLogger;
    public static final double UNLIMITED_TIMEOUT = 100000000;

    public BaseCommand(BaseSubsystem subsystem) {
        this(subsystem, UNLIMITED_TIMEOUT);
    }

    /**
     * Save subclasses from needing to do this code
     * 
     * @param subsystem
     * @param timeOut
     */
    public BaseCommand(BaseSubsystem subsystem, double timeOut) {
        super(timeOut);
        this.dataLogger = DataLoggerFactory.getLoggerFactory().createDataLogger(this.getName());

        if (subsystem != null) {
            requires(subsystem);
        }

    }

    // these methods are protected in the base class.
    // we open them up so that they can be called in unti testing.
    @Override
    public synchronized boolean isRunning() {
        return super.isRunning();
    }

    @Override
    public synchronized boolean isCanceled() {
        return super.isCanceled();
    }

}
