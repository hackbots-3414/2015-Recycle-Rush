package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.Solenoid;

public class MySolenoid implements ISolenoid
{
	Solenoid solenoid;

	public MySolenoid(int channel)
	{
		solenoid = new Solenoid(channel);
	}

	public void enable()
	{
		solenoid.set(true);
	}

	public void disable()
	{
		solenoid.set(false);
	}

	public boolean getEnable()
	{
		return solenoid.get();
	}

}
