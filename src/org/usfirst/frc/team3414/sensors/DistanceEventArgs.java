package org.usfirst.frc.team3414.sensors;

import org.usfirst.frc.team3414.sensors.DistanceMultiplier;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class DistanceEventArgs implements IMeasureDistance, IMeasureDistanceListener

{
	private DistanceMultiplier multiplier;

	// This property holds the event ID
	private long distanceEventID;

	// This property holds the distance value
	private double distance;

	public DistanceEventArgs(DistanceMultiplier multiplier)
	{
		this.multiplier = multiplier;
	}
	/**
	 * Gets the distance to an object in centimeters
	 * 
	 * @generated
	 * @ordered
	 */

	public double getDistanceCm()
	{
		return this.multiplier.adjustToCm(this.distance);
	}

	/**
	 * Gets the distance to an object in feet
	 * 
	 * @generated
	 * @ordered
	 */

	public double getDistanceFt()
	{
		return this.multiplier.adjustToCm(this.distance)*0.0328084;
	}

	/**
	 * Gets the distance to an object in inches
	 * @generated
	 * @ordered
	 */

	public double getDistanceIn()
	{
		return this.multiplier.adjustToCm(this.distance)*0.3937008;
	}
	
	public void distanceEvent(DistanceEventArgs distanceEvent)
	{
	    // TODO Auto-generated method stub
	    
	}
	
	public void addListener(IMeasureDistanceListener listener, long distance)
	{
	    // TODO Auto-generated method stub
	    
	}
	
	public void removeListener(long distanceEventID)
	{
	    // TODO Auto-generated method stub
	    
	}

}