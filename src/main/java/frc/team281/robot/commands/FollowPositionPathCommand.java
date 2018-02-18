package frc.team281.robot.commands;

import java.util.List;

import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.drive.BaseDriveSubsystem;

public class FollowPositionPathCommand extends BaseCommand{

    private BaseDriveSubsystem driveSubsystem;

    protected List<Position> path;
    public FollowPositionPathCommand(BaseDriveSubsystem subsystem, List<Position> path) {
        super(subsystem);
        this.driveSubsystem = subsystem;
        this.path = path;
    }

    @Override
    protected void initialize() {
        for (Position p: path){
            driveSubsystem.getPositionBuffer().addPosition(p);
        }
        
    }
    @Override
    protected boolean isFinished() {
        return ! driveSubsystem.getPositionBuffer().hasNextPosition();
    }
    
}
