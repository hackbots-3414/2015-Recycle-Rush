package org.usfirst.frc.team3414.sensors;

import org.usfirst.frc.team3414.autonomous.SwitchPositions;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The class responsible for controlling limit switches on digital IO ports.
 * Called by AutonomousSwitches.java
 */

public class MyAutoLimitSwitch implements ISwitch
{
	private boolean inverse;
	private SwitchPositions position;
	private DigitalInput limitSwitch;

	public MyAutoLimitSwitch(int channel, boolean inverse)
	{
		this.inverse = inverse;
		this.limitSwitch = new DigitalInput(channel);
		
	}

	public SwitchPositions get()
	{
		if (inverse)
		{
			if (limitSwitch.get())
			{
				position = SwitchPositions.OFF;
			} else
			{
				position = SwitchPositions.ON;
			} 
			return position;
		} else
		{
			if (limitSwitch.get())
			{
				position = SwitchPositions.ON;
			} else
			{
				position = SwitchPositions.OFF;
			} 
			return position;
		}
	}
}