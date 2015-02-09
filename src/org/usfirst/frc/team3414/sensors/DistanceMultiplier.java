package org.usfirst.frc.team3414.sensors;

public class DistanceMultiplier
{
	private double multiplier;

	public DistanceMultiplier(double multiplier)
	{
		this.multiplier = multiplier;
	}
	
	public double adjustToCm(double distance)
	{
		return (distance * this.multiplier);
	}
}
