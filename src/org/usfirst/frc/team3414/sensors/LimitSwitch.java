package org.usfirst.frc.team3414.sensors;
import org.usfirst.frc.team3414.autonomous.SwitchPositions;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The class responsible for controlling limit switches on digital IO ports. 
 */
public class LimitSwitch implements ISwitch
{
	DigitalInput limitSwitch;

	public LimitSwitch(int channel)
	{
		limitSwitch = new DigitalInput(channel);
	}


	public SwitchPositions getMode()
	{
		if (limitSwitch.get())
		{
			return SwitchPositions.ON;
		} else
		{
			return SwitchPositions.OFF;
		}
	}
	
}

