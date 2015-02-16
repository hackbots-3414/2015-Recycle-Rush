package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch implements ILimitSwitch
{
	private DigitalInput limitSwitch;
	private boolean inverse;

	protected LimitSwitch(int channel, boolean inverse)
	{
		limitSwitch = new DigitalInput(channel);
		this.inverse = inverse;
	}

	/**
	 * Assuming Hardware Switches are set to normally Open (How <Big Boy> (Beta) was built)
	 * @return
	 */
	public boolean isHit()
	{
		if (inverse)
		{
			return limitSwitch.get();
		} else
		{
			return !limitSwitch.get();
		}

	}
}