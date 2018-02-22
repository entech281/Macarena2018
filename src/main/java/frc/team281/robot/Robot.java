
package frc.team281.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team281.robot.commands.DriveToPositionCommand;
import frc.team281.robot.commands.FollowPositionPathCommand;
import frc.team281.robot.commands.GrabberCloseCommand;
import frc.team281.robot.commands.GrabberLoadCommand;
import frc.team281.robot.commands.GrabberOpenCommand;
import frc.team281.robot.commands.GrabberShootCommand;
import frc.team281.robot.commands.GrabberStopCommand;
import frc.team281.robot.commands.LifterHomeCommand;
import frc.team281.robot.commands.LifterLowerCommand;
import frc.team281.robot.commands.LifterRaiseCommand;
import frc.team281.robot.commands.LifterStopCommand;
import frc.team281.robot.commands.WristPivotDownCommand;
import frc.team281.robot.commands.WristPivotUpCommand;
import frc.team281.robot.logger.DataLoggerFactory;
import frc.team281.robot.subsystems.FakeGrabberSubsystem;
import frc.team281.robot.subsystems.FakeLifterSubsystem;
import frc.team281.robot.subsystems.FakeWristSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;
import frc.team281.robot.subsystems.LifterSubsystem;
import frc.team281.robot.subsystems.PositionCalculator;
import frc.team281.robot.subsystems.WristSubsystem;
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
    private OperatorInterface operatorInterface;
    private LifterSubsystem lifterSubsystem;
    private GrabberSubsystem grabberSubsystem;
    private WristSubsystem wristSubsystem;
    
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
        lifterSubsystem = new LifterSubsystem();
        grabberSubsystem= new FakeGrabberSubsystem();
        wristSubsystem = new FakeWristSubsystem();
        driveSubsystem.initialize();
        operatorInterface.initialize();
        lifterSubsystem.initialize();
        grabberSubsystem.initialize();
        wristSubsystem.initialize();
    }

    @Override
    public void autonomousInit() {
        driveSubsystem.setMode(DriveMode.POSITION_DRIVE);
        DriveToPositionCommand move1 = new DriveToPositionCommand(driveSubsystem, PositionCalculator.goForward(22.0));
        DriveToPositionCommand move2 = new DriveToPositionCommand(driveSubsystem, PositionCalculator.turnLeft(10.));
        DriveToPositionCommand move3 = new DriveToPositionCommand(driveSubsystem, PositionCalculator.goForward(111.));
        DriveToPositionCommand move4 = new DriveToPositionCommand(driveSubsystem, PositionCalculator.turnRight(10.));
        //DriveToPositionCommand move5 = new DriveToPositionCommand(driveSubsystem, PositionCalculator.goForward(45));
        //DriveToPositionCommand move6 = new DriveToPositionCommand(driveSubsystem, PositionCalculator.turnRight(90));
        //DriveToPositionCommand move7 = new DriveToPositionCommand(driveSubsystem, PositionCalculator.goForward(34));
        CommandGroup m_AutonomousCommand = new CommandGroup();
        m_AutonomousCommand.addSequential(move1);
        m_AutonomousCommand.addSequential(move2);
        m_AutonomousCommand.addSequential(move3);
        m_AutonomousCommand.addSequential(move4);
        //m_AutonomousCommand.addSequential(move5);
        //m_AutonomousCommand.addSequential(move6);
        //m_AutonomousCommand.addSequential(move7);
        m_AutonomousCommand.start();
        
        FollowPositionPathCommand followPath = new FollowPositionPathCommand(driveSubsystem, 
                PositionCalculator.builder()
                .forward(24)
                .left(25)
                .forward(111)
                .build()
        );
        followPath.start();
    }

    public void robotStartingLeft() {
        // if were in mid switch o right then pick auto b
        // makeAutonomousA if switchleft then pick a then whats below 
    }
    
    public void robotStartingMiddle() {
        // if were in mid switch o right then pick auto b
        // makeAutonomousA if switchleft then pick a then whats below  
    }
    
    public void  robotStartingRight() {
        // if were in mid switch o right then pick auto b
        // makeAutonomousA if switchleft then pick a then whats below    
    }
    
    public void autoPathA() {
        FollowPositionPathCommand followPath = new FollowPositionPathCommand(driveSubsystem, 
                PositionCalculator.builder()
                .forward(24)
                .left(25)
                .forward(111)
                .right(35)
                .build()
        );
        followPath.start();
    }
    
    public void autoPathB() {
        FollowPositionPathCommand followPath = new FollowPositionPathCommand(driveSubsystem, 
                PositionCalculator.builder()
                .forward(24)
                .left(25)
                .forward(111)
                .right(35)
                .build()
        );
        followPath.start();   
    }
    
    public void autoPathC() {
        FollowPositionPathCommand followPath = new FollowPositionPathCommand(driveSubsystem, 
                PositionCalculator.builder()
                .forward(24)
                .left(25)
                .forward(111)
                .right(35)
                .forward(84)
                .right(45)
                .forward(52)
                .right(45)
                .forward(110)
                .right(90)
                .forward(41)
                .build()
        );
        followPath.start();    
    }
    
    public void autoPathD() {
        FollowPositionPathCommand followPath = new FollowPositionPathCommand(driveSubsystem, 
                PositionCalculator.builder()
                .forward(24)
                .left(25)
                .forward(111)
                .right(35)
                .build()
        );
        followPath.start();   
    }
    
    public void autoPathE() {
        FollowPositionPathCommand followPath = new FollowPositionPathCommand(driveSubsystem, 
                PositionCalculator.builder()
                .forward(24)
                .left(25)
                .forward(111)
                .right(35)
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

    @Override
    public LifterRaiseCommand createLifterRaiseCommand() {
        return new LifterRaiseCommand(this.lifterSubsystem);
    }

    @Override
    public LifterLowerCommand createLifterLowerCommand() {
        return new LifterLowerCommand(this.lifterSubsystem);
    }

    @Override
    public GrabberLoadCommand createGrabberLoadCommand() {
        return new GrabberLoadCommand(this.grabberSubsystem);
    }

    @Override
    public GrabberShootCommand createGrabberShootCommand() {
        return new GrabberShootCommand(this.grabberSubsystem);
    }

    @Override
    public GrabberStopCommand createGrabberStopCommand() {
        return new GrabberStopCommand(this.grabberSubsystem);
    }

    @Override
    public GrabberOpenCommand createGrabberOpenCommand() {
        return new GrabberOpenCommand(this.grabberSubsystem);
    }

    @Override
    public GrabberCloseCommand createGrabberCloseCommand() {
        return new GrabberCloseCommand(this.grabberSubsystem);
    }

    @Override
    public WristPivotUpCommand createWristPivotUpCommand() {
        return new WristPivotUpCommand(this.wristSubsystem);
    }

    @Override
    public WristPivotDownCommand createWristPivotDownCommand() {
        return new WristPivotDownCommand(this.wristSubsystem);
    }

    @Override
    public LifterHomeCommand createLifterHomeCommand() {
        return new LifterHomeCommand(this.lifterSubsystem);
    }

    @Override
    public LifterStopCommand createLifterStopCommand() {
        return new LifterStopCommand(this.lifterSubsystem);
    }
}
