package frc.team281.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team281.robot.commands.BaseCommand;
import frc.team281.robot.commands.FollowPositionPathCommand;
import frc.team281.robot.commands.GrabberShootCommand;
import frc.team281.robot.commands.LifterRaiseCommand;
import frc.team281.robot.subsystems.GrabberSubsystem;
import frc.team281.robot.subsystems.LifterSubsystem;
import frc.team281.robot.subsystems.PositionCalculator;
import frc.team281.robot.subsystems.drive.RealDriveSubsystem;

public class AutoCommandFactory {

    private LifterSubsystem lifterSubsystem;
    private GrabberSubsystem grabberSubsystem;
    private RealDriveSubsystem driveSubsystem;
    
    public AutoCommandFactory(LifterSubsystem lifterSubsystem, GrabberSubsystem grabberSubsystem, 
            RealDriveSubsystem driveSubsystem) {
        this.lifterSubsystem = lifterSubsystem;
        this.grabberSubsystem = grabberSubsystem;
        this.driveSubsystem = driveSubsystem;
    }
    
    public CommandGroup makeAutoCommand(WhichAutoCodeToRun whatAutoToRun) {
        
        switch (whatAutoToRun) {
        case A: whatAutoToRun = WhichAutoCodeToRun.A;
            //return makeAutoCommand(auto);
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
    
    protected CommandGroup makeAutoCommand(BaseCommand pathCommand) {
       CommandGroup auto = new CommandGroup();
           auto.addSequential(pathCommand);
           auto.addSequential(new LifterRaiseCommand(lifterSubsystem));
           auto.addSequential(new GrabberShootCommand(grabberSubsystem, 2000));
       return auto;  
    }
    
    public BaseCommand autoPathA() {
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
        return followPath;
    }
    
    public BaseCommand autoPathB() {
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
        return followPath;
    }
    
    public BaseCommand autoPathC() {
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
        return followPath;
    }
    
    public BaseCommand autoPathD() {
        FollowPositionPathCommand followPath = new FollowPositionPathCommand(driveSubsystem, 
                PositionCalculator.builder()
                .forward(24)
                .left(42)
                .forward(82)
                .right(40)
                .forward(45)
                .build()
        ); 
        return followPath;
    }
    
    public BaseCommand autoPathE() {
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
        return followPath;
    }

}
