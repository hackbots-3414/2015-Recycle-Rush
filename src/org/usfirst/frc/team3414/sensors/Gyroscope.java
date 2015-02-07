package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.Gyro;
import org.usfirst.frc.team3414.teleop.Display;

public class Gyroscope
{
	
	private double angleOffset = 0;
	private double rateOffset = 1;
	
	private Gyro OnlyGyro;

	public Gyroscope(int channel)
	{
		OnlyGyro = new Gyro(channel);
		OnlyGyro.initGyro();
	}
	
	public double getAngle()
	{
		return(OnlyGyro.getAngle() + angleOffset);
	}
	
	public double getRate()
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

	public void display() {
		Display.getInstance().setGyroData(this.getAngle(), this.getRate());
	}

}