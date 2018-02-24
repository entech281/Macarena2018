package frc.team281.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team281.robot.commands.FollowPositionPathCommand;
import frc.team281.robot.subsystems.PositionCalculator;
import frc.team281.robot.subsystems.GrabberSubsystem;
import frc.team281.robot.subsystems.LifterSubsystem;
import frc.team281.robot.subsystems.WristSubsystem;
import frc.team281.robot.subsystems.drive.RealDriveSubsystem;

public class AutoCommandFactory {

    private LifterSubsystem lifterSubsystem;
    private GrabberSubsystem grabberSubsystem;
    private WristSubsystem wristSubsystem;
    private RealDriveSubsystem driveSubsystem;
    
    public AutoCommandFactory(LifterSubsystem lifterSubsystem, GrabberSubsystem grabberSubsystem, 
            WristSubsystem wristSubsystem, RealDriveSubsystem driveSubsystem) {
        this.lifterSubsystem = lifterSubsystem;
        this.grabberSubsystem = grabberSubsystem;
        this.wristSubsystem = wristSubsystem;
        this.driveSubsystem = driveSubsystem;
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
                .forward(40)
                .right(90)
                .forward(42)
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
                .forward(74)
                .right(20)
                .forward(96)
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
                .left(42)
                .forward(82)
                .right(40)
                .forward(45)
                .build()
        );
        followPath.start();   
    }
    
    public void autoPathE() {
        FollowPositionPathCommand followPath = new FollowPositionPathCommand(driveSubsystem, 
                PositionCalculator.builder()
                .forward(24)
                .left(50)
                .forward(114)
                .right(70)
                .forward(50)
                .right(35)
                .forward(74)
                .right(20)
                .forward(96)
                .build()
        );
        followPath.start();   
    }

}
