package frc.team281.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team281.robot.commands.GrabberCloseCommand;
import frc.team281.robot.commands.GrabberLoadCommand;
import frc.team281.robot.commands.GrabberOpenCommand;
import frc.team281.robot.commands.GrabberShootCommand;
import frc.team281.robot.commands.GrabberStopCommand;
import frc.team281.robot.commands.LifterHomeCommand;
import frc.team281.robot.commands.LifterLowerCommand;
import frc.team281.robot.commands.LifterRaiseCommand;
import frc.team281.robot.commands.WristPivotDownCommand;
import frc.team281.robot.commands.WristPivotUpCommand;
import frc.team281.robot.logger.DataLoggerFactory;
import frc.team281.robot.subsystems.FakeGrabberSubsystem;
import frc.team281.robot.subsystems.FakeLifterSubsystem;
import frc.team281.robot.subsystems.FakeWristSubsystem;
import frc.team281.robot.subsystems.GrabberSubsystem;
import frc.team281.robot.subsystems.LifterSubsystem;
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
    private WhichAutoCodeToRun whatAutoToRun;
    
    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    
    public void s() {

        // create the objects for the real match
        DataLoggerFactory.configureForMatch();

        operatorInterface = new OperatorInterface(this);
        driveSubsystem = new RealDriveSubsystem(operatorInterface);
        lifterSubsystem = new FakeLifterSubsystem();
        grabberSubsystem= new FakeGrabberSubsystem();
        wristSubsystem = new FakeWristSubsystem();
        driveSubsystem.initialize();
        operatorInterface.initialize();
        lifterSubsystem.initialize();
        grabberSubsystem.initialize();
        wristSubsystem.initialize();
        
        String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
        FieldMessage fieldMessage = new FieldMessageGetter().convertGameMessageToFieldMessage(gameMessage); 
        whatAutoToRun = new ConvertFieldMessageToCommandGroup().convert(fieldMessage);
        
    }

    @Override
    public void autonomousInit() {
    		SmartDashboard.putString("Selected Auto", whatAutoToRun+"");
        driveSubsystem.setMode(DriveMode.POSITION_DRIVE);

        AutoCommandFactory af = new AutoCommandFactory(lifterSubsystem, grabberSubsystem, driveSubsystem);
        CommandGroup autoCommand = af.makeAutoCommand(whatAutoToRun);
        autoCommand.start();
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

}
