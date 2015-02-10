package org.usfirst.frc.team3414.sensors;

import org.usfirst.frc.team3414.autonomous.SwitchPositions;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The class responsible for controlling limit switches on digital IO ports.
 */
public class LimitSwitch implements ISwitch
{
	DigitalInput limitSwitch;
	boolean inverse;

	public LimitSwitch(int channel, boolean inverse)
	{
		limitSwitch = new DigitalInput(channel);
		this.inverse = inverse;
	}

	public SwitchPositions get()
	{
		if (inverse)
		{
			if(limitSwitch.get())
			{
				return SwitchPositions.OFF;
			} else
			{
				return SwitchPositions.ON;
			}
		} else
		{
			if(limitSwitch.get())
			{
				return SwitchPositions.ON;
			}else
			{
				return SwitchPositions.OFF;
			}
		}
	}
}