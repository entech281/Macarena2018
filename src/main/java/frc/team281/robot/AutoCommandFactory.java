package frc.team281.robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team281.robot.commands.BaseCommand;
import frc.team281.robot.commands.FollowPositionPathCommand;
import frc.team281.robot.commands.GrabberShootCommand;
import frc.team281.robot.commands.WristPivotDownCommand;
import frc.team281.robot.commands.LifterRaiseCommand;
import frc.team281.robot.commands.LifterRaiseSeconds;
import frc.team281.robot.subsystems.GrabberSubsystem;
import frc.team281.robot.subsystems.WristSubsystem;
import frc.team281.robot.subsystems.LifterSubsystem;
import frc.team281.robot.subsystems.PositionCalculator;
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
            return makeAutoProcedure(autoPathA());
            
        case B: whatAutoToRun = WhichAutoCodeToRun.B;
            return makeAutoProcedure(autoPathB());
             
        case C: whatAutoToRun = WhichAutoCodeToRun.C;
            return makeAutoProcedure(autoPathC());
            
        case D: whatAutoToRun = WhichAutoCodeToRun.D;
            return makeAutoProcedure(autoPathE());
            
        case E: whatAutoToRun = WhichAutoCodeToRun.E;
            return makeAutoProcedure(autoPathE());
            
        case F: whatAutoToRun = WhichAutoCodeToRun.F;
            return makeAutoProcedure(autoPathF());
            
        default:
            break;
        }
        return null;
    }
    
    protected CommandGroup makeAutoProcedure(BaseCommand followPath) {
       CommandGroup auto = new CommandGroup();
           auto.addParallel(new WristPivotDownCommand(wristSubsystem));
           auto.addParallel(new LifterRaiseSeconds(lifterSubsystem,0.25));
           auto.addSequential(followPath);
           auto.addSequential(new GrabberShootCommand(grabberSubsystem, 2));
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
                .forward(138)
                .build()
        ); 
        return followPath;
    }
    
    public BaseCommand autoPathE() {
        FollowPositionPathCommand followPath = new FollowPositionPathCommand(driveSubsystem, 
                PositionCalculator.builder()
                .forward(138)
                .build()
        );  
        return followPath;
    }
    
    public BaseCommand autoPathF() {
        FollowPositionPathCommand followPath = new FollowPositionPathCommand(driveSubsystem, 
                PositionCalculator.builder()
                .forward(138)
                .left(25)     
                .forward(111)     
                .right(35)        
                .forward(84)      
                .right(45)        
                .forward(52)      
                .right(45)        
                .forward(130)     
                .left(90)     
                .forward(41)
                .build()
        );
        return followPath;
    }
}
