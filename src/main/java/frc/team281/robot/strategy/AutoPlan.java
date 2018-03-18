package frc.team281.robot.strategy;

import java.util.List;

import frc.team281.robot.subsystems.Position;

public class AutoPlan {

    public AutoPlan(String name, boolean isTargetScale, boolean shouldDropCube, List<Position> path){
        this.name = name;
        this.path = path;
        this.shouldDropCube = shouldDropCube;
        this.isTargetingScale = isTargetScale;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMirror(boolean mirror){
        this.mirror = mirror;
    }
    
    public boolean shouldMirror(){
        return this.mirror;
    }
    public List<Position> getPath() {
        return path;
    }
    public void setPath(List<Position> path) {
        this.path = path;
    }
    public boolean isTargetingScale() {
        return isTargetingScale;
    }
    public boolean isTargetingSwitch(){
        return ! isTargetingScale;
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
    protected List<Position> path;
    protected boolean isTargetingScale = false;
    protected boolean shouldDropCube = false;
    protected boolean mirror= false;
    protected String name = "";
    public String toString(){
        return "AutoPlan: Path=" + name + ",scale=" + this.isTargetingScale + ",shouldDrop=" + shouldDropCube;
    }
    
}
