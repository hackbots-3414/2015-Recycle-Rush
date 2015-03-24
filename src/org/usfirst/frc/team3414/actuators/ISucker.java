package org.usfirst.frc.team3414.actuators;

public interface ISucker
{
    public void in();
    public void out();
    public void right();
    public void left();
    public void stop();
    public void setInSpeed(double speed);
}
