
package frc.team281.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team281.robot.commands.DriveToPositionCommand;
import frc.team281.robot.commands.FollowPositionPathCommand;
import frc.team281.robot.logger.DataLoggerFactory;
import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.PositionCalculator;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team281.robot.subsystems.RealLifterSubsystem;
import frc.team281.robot.subsystems.drive.BaseDriveSubsystem.DriveMode;
import frc.team281.robot.subsystems.drive.RealDriveSubsystem;

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
public class Robot extends IterativeRobot implements CommandFactory {

    private RealDriveSubsystem driveSubsystem;
    private RealLifterSubsystem lifterSubsystem;
    private OperatorInterface operatorInterface;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {

        // create the objects for the real match
        DataLoggerFactory.configureForMatch();

        lifterSubsystem = new RealLifterSubsystem();

        operatorInterface = new OperatorInterface(this);
        driveSubsystem = new RealDriveSubsystem(operatorInterface);

        driveSubsystem.initialize();
        operatorInterface.initialize();
    }

    @Override
    public void autonomousInit() {
        driveSubsystem.setMode(DriveMode.POSITION_DRIVE);
        DriveToPositionCommand move1 = new DriveToPositionCommand(driveSubsystem, PositionCalculator.goForward(60.0));
        DriveToPositionCommand move2 = new DriveToPositionCommand(driveSubsystem, PositionCalculator.turnLeft(90));
        DriveToPositionCommand move3 = new DriveToPositionCommand(driveSubsystem, PositionCalculator.goForward(140));
        //DriveToPositionCommand move3 = new DriveToPositionCommand(driveSubsystem, calculator.goForward(108.));
        //DriveToPositionCommand move4 = new DriveToPositionCommand(driveSubsystem, calculator.turnRight(55));
        //DriveToPositionCommand move5 = new DriveToPositionCommand(driveSubsystem, calculator.goForward(44));
        //DriveToPositionCommand move6 = new DriveToPositionCommand(driveSubsystem, calculator.turnRight(90));
        //DriveToPositionCommand move7 = new DriveToPositionCommand(driveSubsystem, calculator.goForward(21));
        CommandGroup m_AutonomousCommand = new CommandGroup();
        m_AutonomousCommand.addSequential(move1);
        m_AutonomousCommand.addSequential(move2);
        m_AutonomousCommand.addSequential(move3);
            //m_AutonomousCommand.addSequential(move4);
            //m_AutonomousCommand.addSequential(move5);
            //m_AutonomousCommand.addSequential(move6);
            //m_AutonomousCommand.addSequential(move7);


        //m_AutonomousCommand.start();
        
        FollowPositionPathCommand followPath = new FollowPositionPathCommand(driveSubsystem, 
                PositionCalculator.builder()
                .forward(60)
                .left(90)
                .forward(40)
                .build()
        );
        followPath.start();
    }


    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        driveSubsystem.setMode(DriveMode.DISABLED);
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        driveSubsystem.setMode(DriveMode.SPEED_DRIVE);
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

}
