package org.usfirst.frc.team3414.sensors;

import org.usfirst.frc.team3414.autonomous.SwitchPositions;

/**
 * The class responsible for controlling limit switches on digital IO ports.
 */

public class MyAutoLimitSwitch
{
	private ISwitch iSwitch;
	private boolean inverse;
	private SwitchPositions position;

	public MyAutoLimitSwitch(int channel, boolean inverse)
	{
		this.inverse = inverse;
	}

	public SwitchPositions get()
	{
		if (inverse)
		{
			if (iSwitch.get() == SwitchPositions.ON)
			{
				position = SwitchPositions.OFF;
			} else if (iSwitch.get() == SwitchPositions.OFF)
			{
				position = SwitchPositions.ON;
			} 
			return position;
		} else
		{
			return iSwitch.get();
		}
	}
}