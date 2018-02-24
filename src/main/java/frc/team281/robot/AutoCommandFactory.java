package frc.team281.robot;

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
    
    public AutoCommandFactory() {
        
    }
    
    public CommandGroup makeAutoCommand(WhichAutoCodeToRun whatAutoToRun) {
        
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
        return null;
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
