package frc.team281.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team281.robot.commands.FollowPositionPathCommand;
import frc.team281.robot.subsystems.PositionCalculator;
import frc.team281.robot.subsystems.GrabberSubsystem;
import frc.team281.robot.subsystems.LifterSubsystem;
import frc.team281.robot.subsystems.WristSubsystem;
import frc.team281.robot.subsystems.drive.RealDriveSubsystem;

public class AutoCommandFactory {

    private RealDriveSubsystem driveSubsystem;
    private LifterSubsystem lifterSubsystem;
    private OperatorInterface operatorInterface;
    private GrabberSubsystem grabberSubsystem;
    private WristSubsystem wristSubsystem;
    private WhichAutoCodeToRun whatAutoToRun;
    
    public AutoCommandFactory() {
        
        lifterSubsystem = new LifterSubsystem();
        grabberSubsystem= new GrabberSubsystem();
        wristSubsystem = new WristSubsystem();
        driveSubsystem = new RealDriveSubsystem(operatorInterface);
        
        driveSubsystem.initialize();
        lifterSubsystem.initialize();
        grabberSubsystem.initialize();
        wristSubsystem.initialize();
        
        String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
        FieldMessage fieldMessage = new FieldMessageGetter().convertGameMessageToFieldMessage(gameMessage); 
        whatAutoToRun = new ConvertFieldMessageToCommandGroup().convert(fieldMessage);
        
    }
    
    public class MakeAutoCommand extends CommandGroup {
        public MakeAutoCommand() {
            
        }
    }
    
    public void baseAutoRoutine() {
        
        switch (whatAutoToRun) {
        case A: whatAutoToRun = WhichAutoCodeToRun.A;
            break;
            
        case B: whatAutoToRun = WhichAutoCodeToRun.B;
            break; 
            
        case C: whatAutoToRun = WhichAutoCodeToRun.C;
            break;
            
        case D: whatAutoToRun = WhichAutoCodeToRun.D; 
            break;
            
        case E: whatAutoToRun = WhichAutoCodeToRun.E;
            break;
            
        default:
            break;
        }
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

}
