package frc.team281.robot.commands;

import java.util.List;

import frc.team281.robot.subsystems.Position;
import frc.team281.robot.subsystems.drive.BaseDriveSubsystem;

public class FollowPositionPathCommand extends BaseCommand{

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FollowPositionPathCommand other = (FollowPositionPathCommand) obj;
        if (path == null) {
            if (other.path != null)
                return false;
        } else if (!path.equals(other.path))
            return false;
        return true;
    }
    public BaseDriveSubsystem driveSubsystem;
    public List<Position> path;
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
