package org.usfirst.frc.team3414.sensors;


public class DistanceEventArgs
{
	/**
	 */
	private long distanceEventID;
	
	/**
	 */
	private double distance;
	
	/**
	 */
	public DistanceEventArgs(long distanceEventID, double distance) 
	{
		super();
		this.distanceEventID = distanceEventID;
		this.distance = distance;
	}

	/**
	 */
	public long getDistanceEventID() 
	{
		return distanceEventID;
	}
	
	/**
	 */
	public double getDistanceCm() 
	{
		
		return distance;
	}
	
	/**
	 */
	public double getDistanceFt() 
	{
	
		return DistanceConversion.inToFeet(getDistanceIn());
	}
	
	/**
	 */
	public double getDistanceIn() 
	{
		return DistanceConversion.cmToInches(distance);
	}	
}

