package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.Encoder;

public class MyEncoder
{
	private Encoder encode;

	public MyEncoder(int channelPortA, int channelPortB)
	{
		encode = new Encoder(channelPortA, channelPortB);
	}

	public int getPosition()
	{
		return encode.getRaw();
	}

	public boolean getDirection()
	{
		return encode.getDirection();
	}

	public double getDistance()
	{
		return encode.getDistance();
	}

	public void setDistancePerPulse(double distancePerPulse)
	{
		encode.setDistancePerPulse(distancePerPulse);
	}

	public double getRate()
	{
		return encode.getRate();
	}

	public boolean getStopped()
	{
		return encode.getStopped();
	}

	public void reset()
	{
		encode.reset();
	}
}
