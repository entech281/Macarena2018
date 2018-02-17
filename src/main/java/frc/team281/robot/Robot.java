
package frc.team281.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team281.robot.commands.JoystickDriveCommand;
import frc.team281.robot.commands.DriveToPositionCommand;
import frc.team281.robot.logger.DataLoggerFactory;
import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.RealDriveSubsystem;

/**
 * The robot, only used in the real match. Cannot be instantiated outside of the
 * match, so we want to minimize its functionality here.
 * 
 * In short-- anything in here can't be tested outside of running the real
 * robot, so we want to be careful.
 * 
 * Since the robot knows about its subsystems, it makes sense for Robot to
 * implement CommandFactory-- though that is not strictly necessary. In fact, it
 * would be easy to move all of the subsystems out into another class, and have
 * that one implement CommandFactory
 */
public class Robot extends TimedRobot implements CommandFactory {

    private RealDriveSubsystem driveSubsystem;
    private OperatorInterface operatorInterface;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {

        // create the objects for the real match
        DataLoggerFactory.configureForMatch();

        operatorInterface = new OperatorInterface(this);
        driveSubsystem = new RealDriveSubsystem(operatorInterface);

        driveSubsystem.initialize();
        operatorInterface.initialize();
    }

    @Override
    public void autonomousInit() {
        // m_AutonomousCommand.start();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {

    }

    /**
     * This function is called once each time the robot enters Disabled mode. You
     * can use it to reset any subsystem information you want to clear when the
     * robot is disabled.
     */

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public JoystickDriveCommand createDriveCommand() {
        return new JoystickDriveCommand(this.driveSubsystem, operatorInterface);
    }

    @Override
    public DriveToPositionCommand createPositionCommand() {
        return new DriveToPositionCommand(this.driveSubsystem, new Position(120,120));
    }

    @Override
    public LifterRaiseCommand createLifterRaiseCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LifterLowerCommand createLifterLowerCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    // @Override
    // public LifterStopCommand createLifterStopCommand() {
        // TODO Auto-generated method stub
    //     return null;
    // }

    @Override
    public LifterScaleHighCommand createLifterScaleHighCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LifterScaleMidCommand createLifterScaleMidCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LifterScaleLowCommand createLifterScaleLowCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LifterFenceCommand createLifterFenceCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LifterGroundCommand createLifterGroundCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GrabberLoadCommand createGrabberLoadCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GrabberShootCommand createGrabberShootCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GrabberStopCommand createGrabberStopCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GrabberOpenCommand createGrabberOpenCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GrabberCloseCommand createGrabberCloseCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WristPivotUpCommand createWristPivotUpCommand() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WristPivotDownCommand createWristPivotDownCommand() {
        // TODO Auto-generated method stub
        return null;
    }
}
