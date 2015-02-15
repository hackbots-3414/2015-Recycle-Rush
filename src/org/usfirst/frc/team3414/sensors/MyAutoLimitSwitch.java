package org.usfirst.frc.team3414.sensors;

import org.usfirst.frc.team3414.autonomous.SwitchPositions;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The class responsible for controlling limit switches on digital IO ports.
 */

public class MyAutoLimitSwitch
{
	private DigitalInput limitSwitch;
	private ISwitch iSwitch;
	private boolean inverse;
	private SwitchPositions position;

	public MyAutoLimitSwitch(int channel, boolean inverse)
	{
		limitSwitch = new DigitalInput(channel);
		this.inverse = inverse;
	}

	public SwitchPositions get()
	{
		if (inverse)
		{
			if (iSwitch.get() == SwitchPositions.ON)
			{
				return SwitchPositions.OFF;
			} else if (iSwitch.get() == SwitchPositions.OFF)
			{
				return SwitchPositions.ON;
			} else {
				return SwitchPositions.ON;	//Default
			}
		} else
		{
			return iSwitch.get();
		}
	}
}