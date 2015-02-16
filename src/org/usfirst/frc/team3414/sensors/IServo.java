package org.usfirst.frc.team3414.sensors;

public interface IServo
{
    public void engage();

    public void disengage();
    
    public boolean getEngaged();
    
    public double getAngle();
}