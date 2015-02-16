package org.usfirst.frc.team3414.sensors;


public class Encoder implements IEncoder
{
	private edu.wpi.first.wpilibj.Encoder encoder;

	public Encoder(int channelPortA, int channelPortB)
	{
		encoder = new edu.wpi.first.wpilibj.Encoder(channelPortA, channelPortB);
	}

	/**
	 * Uses Encoder.getRaw()
	 * @return
	 */
	public int getPosition()
	{
		return encoder.getRaw();
	}

	public boolean getDirection()
	{
		return encoder.getDirection();
	}

	/**
	 * Difference between getDistance() and getPosition(): getDistance() is multiplied by setDistancePerPulse()
	 * @return
	 */
	public double getDistance()
	{
		return encoder.getDistance();
	}

	public void setDistancePerPulse(double distancePerPulse)
	{
		encoder.setDistancePerPulse(distancePerPulse);
	}

	/**
	 * Per Second
	 * @return
	 */
	public double getRate()
	{
		return encoder.getRate();
	}

	public boolean getStopped()
	{
		return encoder.getStopped();
	}

	public void reset()
	{
		encoder.reset();
	}
}
