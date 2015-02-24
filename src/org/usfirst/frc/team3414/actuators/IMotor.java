package org.usfirst.frc.team3414.actuators;

/**
 * implemented by Motor.java
 */
public interface IMotor
{
    public void stop();

    public void down(double speed);

    public void upGradual(double speed, int motorStep);

    public void up(double speed);

    public void downGradual(double speed, int motorStep);

    public double getSpeed();
	
}



