package frc.team281.robot;


import java.util.List;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team281.robot.commands.BaseCommand;
import frc.team281.robot.commands.DriveToPositionCommand;
import frc.team281.robot.commands.DriveForwardUntilCollisionCommand;
import frc.team281.robot.commands.DriveForwardNoEncodersCommand;
import frc.team281.robot.commands.FollowPositionPathCommand;
import frc.team281.robot.commands.GrabberShootCommand;
import frc.team281.robot.commands.GrabberOpenCommand;
import frc.team281.robot.commands.WristPivotDownCommand;
import frc.team281.robot.strategy.AutoPlan;
import frc.team281.robot.commands.LifterRaiseSeconds;
import frc.team281.robot.commands.LifterTopCommand;
import frc.team281.robot.subsystems.GrabberSubsystem;
import frc.team281.robot.subsystems.WristSubsystem;
import frc.team281.robot.subsystems.LifterSubsystem;
import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.PositionCalculator;
import frc.team281.robot.subsystems.drive.RealDriveSubsystem;

public class AutoCommandFactory {

    public static final double FORWARD_MOVE_INCHES = 10.0;
    public static final double FORWARD_MOVE_TIMEOUT = 2.0;
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
    
    public CommandGroup makeAutoCommand(AutoPlan autoPlan) {
        List<Position> path = autoPlan.getPath();

        if ( autoPlan.shouldMirror()){
            path = PositionCalculator.mirror(path);
        }
        
        CommandGroup auto = new CommandGroup();
        if (autoPlan.isTargetingScale()) {
            auto.addParallel(new LifterTopCommand(lifterSubsystem));
        } else {
            auto.addParallel(new LifterRaiseSeconds(lifterSubsystem,1.5));
        }
        auto.addSequential(new FollowPositionPathCommand( driveSubsystem, path));
        //TODO: we discussed having this be drive forward open loop,
        //but to do that, we have to change into speed control mode. That currently happens
        //in teleopInit. We can't do it here because we're creaeting the command, so we'd
        //have to add the mode change into the command itself, which is a bit scary.
        //so i'd rather just use closed position mode to drive forward

        if (autoPlan.isTargetingSwitch()) {
            auto.addSequential(new DriveForwardNoEncodersCommand(driveSubsystem,3.0,0.6));
        } else {
            auto.addSequential(new DriveToPositionCommand(driveSubsystem,
                        new Position(FORWARD_MOVE_INCHES,FORWARD_MOVE_INCHES),
                        FORWARD_MOVE_TIMEOUT));
        }

        if ( autoPlan.isShouldDropCube()){
            if (autoPlan.isTargetingScale()) {
                auto.addSequential(new WristPivotDownCommand(wristSubsystem));
                auto.addSequential(new GrabberOpenCommand(grabberSubsystem, 2));
            } else {
                auto.addSequential(new WristPivotDownCommand(wristSubsystem));
                auto.addSequential(new GrabberShootCommand(grabberSubsystem, 10.0));
            }
        }
        auto.addSequential(new DriveForwardNoEncodersCommand(driveSubsystem,0.5,-0.4));
        return auto;
    }
    
    protected CommandGroup makeAutoProcedure(BaseCommand followPath) {
       CommandGroup auto = new CommandGroup();
       auto.addSequential(followPath);
       return auto;
    }
}
