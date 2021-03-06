 package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.Gyro;

public class Gyroscope implements IMeasureDirection
{
	
	private double angleOffset = 0;
	private double rateOffset = 1;
	
	private Gyro OnlyGyro;

	public Gyroscope(int channel)
	{
		OnlyGyro = new Gyro(channel);
		OnlyGyro.initGyro();
	}
	
	public double getDegrees()
	{
		return(OnlyGyro.getAngle() + angleOffset);
	}
	/*
	 * Rate of change of angle per second
	 */
	public double getChangeInDegreesPerSecond()
	{
		return (OnlyGyro.getRate() * rateOffset);
	}
	
	public void setSensitivity(double voltsPerDegreePerSecond)
	{
		OnlyGyro.setSensitivity(voltsPerDegreePerSecond);
	}

	public void reset()
	{
		OnlyGyro.reset();
	}

	public double getRadians()
	{
	   return (OnlyGyro.getAngle() + angleOffset) * Math.PI / 180;
	}
	
	
}