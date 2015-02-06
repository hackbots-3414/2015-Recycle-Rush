package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.Gyro;
import org.usfirst.frc.team3414.teleop.Display;
import org.usfirst.frc.team3414.teleop.Log;

public class Gyroscope implements IMeasureDirection
{
	double xDegrees = 0;
	
	private Gyro OnlyGyro;

	public Gyroscope(int channel)
	{
		OnlyGyro = new Gyro(channel);
		initGyro();
	}

	public void getangle()
	{
		return (OnlyGyro.get() + xDegrees );
	}

	private void initGyro()
	{

	}

	public void autonomousperiodic()
	{

	}

	public void autonomousInit()
	{

	}

	public void teleopInit()
	{

	}

	public void teleopperiodic()
	{

	}

	public double getChangeInDirection()
	{
		return 0;
	}

	public double getDegreesX()
	{
		return (OnlyGyro.getX() + xDegrees );
	}

	public double getRadians()
	{
		return 0;
	}

	public Display display;

	public Log log;

}
