package frc.team281.robot.strategy;

import frc.team281.robot.WhichAutoCodeToRun;

public class AutoPlan {

    public AutoPlan( boolean isTargetScale, boolean shouldDropCube, WhichAutoCodeToRun path ){
        this.path = path;
        this.shouldDropCube = shouldDropCube;
        this.isTargetingScale = isTargetScale;
    }
    
    public WhichAutoCodeToRun getPath() {
        return path;
    }
    public void setPath(WhichAutoCodeToRun path) {
        this.path = path;
    }
    public boolean isTargetingScale() {
        return isTargetingScale;
    }
    public void setTargetingScale(boolean isTargetingScale) {
        this.isTargetingScale = isTargetingScale;
    }
    public boolean isShouldDropCube() {
        return shouldDropCube;
    }
    public void setShouldDropCube(boolean shouldDropCube) {
        this.shouldDropCube = shouldDropCube;
    }
    protected WhichAutoCodeToRun path;
    protected boolean isTargetingScale = false;
    protected boolean shouldDropCube = false;
    
    public String toString(){
        return "AutoPlan: Path=" + path + ",scale=" + this.isTargetingScale + ",shouldDrop=" + shouldDropCube;
    }
    
}
