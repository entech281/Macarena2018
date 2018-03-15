package frc.team281.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import frc.team281.robot.commands.GrabberCloseCommand;
import frc.team281.robot.commands.GrabberLoadCommand;
import frc.team281.robot.commands.GrabberOpenCommand;
import frc.team281.robot.commands.GrabberShootCommand;
import frc.team281.robot.commands.GrabberStopCommand;
import frc.team281.robot.commands.LifterHomeCommand;
import frc.team281.robot.commands.LifterTopCommand;
import frc.team281.robot.commands.PushOutCubeAndOpenCommand;
import frc.team281.robot.commands.LifterLowerCommand;
import frc.team281.robot.commands.LifterRaiseCommand;
import frc.team281.robot.commands.LifterStopCommand;
import frc.team281.robot.commands.WristPivotDownCommand;
import frc.team281.robot.commands.WristPivotUpCommand;
import frc.team281.robot.commands.LifterRaiseSeconds;
import frc.team281.robot.logger.DataLoggerFactory;
import frc.team281.robot.strategy.AutoStrategy;
import frc.team281.robot.strategy.AutoStrategySelector;
import frc.team281.robot.subsystems.GrabberSubsystem;
import frc.team281.robot.subsystems.LifterSubsystem;
import frc.team281.robot.subsystems.WristSubsystem;
import frc.team281.robot.subsystems.drive.BaseDriveSubsystem.DriveMode;
import frc.team281.robot.subsystems.drive.RealDriveSubsystem;
import frc.team281.robot.RobotMap.DigitalIO;
import frc.team281.robot.commands.CloseAndIntakeCommand;
import frc.team281.robot.commands.DriveForwardNoEncodersCommand;
import frc.team281.robot.commands.TurnRightNoEncodersCommand;


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
    private DriveForwardNoEncodersCommand DFNEC;
    private CommandGroup DRAR;
    private Compressor compressor;
    private AutoStrategySelector autoStrategySelector = new AutoStrategySelector();
    DigitalInput leftPositionSwitch = new DigitalInput(DigitalIO.LEFT_SWITCH_POSITION);
    DigitalInput rightPositionSwitch = new DigitalInput(DigitalIO.RIGHT_SWITCH_POSITION);
    DigitalInput preferenceSwitch = new DigitalInput(DigitalIO.PREFERENCE_SWITCH);

    
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
        grabberSubsystem= new GrabberSubsystem();
        wristSubsystem = new WristSubsystem();
        driveSubsystem.initialize();
        operatorInterface.initialize();
        lifterSubsystem.initialize();
        grabberSubsystem.initialize();
        wristSubsystem.initialize();
        compressor = new Compressor(RobotMap.CAN.PC_MODULE);
        compressor.start();
        setupCommands();        

    }

    protected void setupCommands(){
        DRAR = new CommandGroup();
        DRAR.addSequential(new LifterRaiseSeconds(lifterSubsystem,0.4));
        DRAR.addSequential(new DriveForwardNoEncodersCommand(driveSubsystem, 1.6, .75));
        if (!leftPositionSwitch.get()) {
            DRAR.addSequential(new TurnRightNoEncodersCommand(driveSubsystem, 0.5, 0.4));
        } else if (!rightPositionSwitch.get()) {
            DRAR.addSequential(new TurnRightNoEncodersCommand(driveSubsystem, 0.5, -0.4));          
        }
        DRAR.addSequential(new WristPivotDownCommand(wristSubsystem));
        DFNEC = new DriveForwardNoEncodersCommand(driveSubsystem, 1.6, .75);        
    }
    
    @Override
    public void autonomousInit() {            	
        WhichAutoCodeToRun whatAutoToRun = selectAutoToRun();         
    	SmartDashboard.putString("Selected Auto", whatAutoToRun+"");

        driveSubsystem.setMode(DriveMode.POSITION_DRIVE);

        AutoCommandFactory af = new AutoCommandFactory(lifterSubsystem, grabberSubsystem, wristSubsystem, driveSubsystem);
        CommandGroup autoCommand = af.makeAutoCommand(WhichAutoCodeToRun.C_MIRRORED);
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
        SmartDashboard.putString("SelectedAuto", selectAutoToRun()+"");
        Scheduler.getInstance().run();
    }

    protected WhichAutoCodeToRun selectAutoToRun(){
        
        String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
        FieldMessage fm = new FieldMessageGetter(leftPositionSwitch.get(), rightPositionSwitch.get()).convertGameMessageToFieldMessage(gameMessage);
        
        //the auto selector allows us to select strategies via different methods,
        //or hard-code the strategy here to make sure it works
        AutoStrategy strategy = autoStrategySelector.selectStrategyFromButtons(fm, true,false,false);
        
        //AutoStrategy strategy = autoStrategySelector.handleScale();
        //AutoStrategy strategy = autoStrategySelector.handleSwitch();
        
        WhichAutoCodeToRun selectedAuto = strategy.getAutoPath(fm);
        return selectedAuto;
        
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
    public LifterTopCommand createLifterTopCommand() {
        return new LifterTopCommand(this.lifterSubsystem);
    }

    public LifterStopCommand createLifterStopCommand() {
    	return new LifterStopCommand(this.lifterSubsystem);
    }

    @Override
    public CloseAndIntakeCommand createCloseAndIntakeCommand() {
        return new CloseAndIntakeCommand(this.grabberSubsystem);
    }

    @Override
    public PushOutCubeAndOpenCommand createPushOutCubeAndOpenCommand() {
        return new PushOutCubeAndOpenCommand(this.grabberSubsystem);
    }
}
