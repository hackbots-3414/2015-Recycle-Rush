package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The class responsible for controlling limit switches on digital IO ports.
 */

public class LimitSwitch
{
	DigitalInput limitSwitch;
	boolean inverse;

	public LimitSwitch(int channel, boolean inverse)
	{
		limitSwitch = new DigitalInput(channel);
		this.inverse = inverse;
	}

	public boolean get()
	{
		if (inverse)
		{
			return !limitSwitch.get();
		} else
		{
			return limitSwitch.get();
		}
	}
}