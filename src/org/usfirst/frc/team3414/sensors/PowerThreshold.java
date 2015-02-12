package org.usfirst.frc.team3414.sensors;


/**
 *
 *
 * @generated
 */

public class PowerThreshold
{
	/**
	 * 
	 * The lowest acceptable value of current or voltage
	 * @generated
	 * @ordered
	 */
	
	public double low;
	
	/**
	 *
	 * The highest acceptable value of current or voltage
	 * @generated
	 * @ordered
	 */
	
	public double high;
	
	public PowerThreshold(double high, double low)
	{
		this.low = low;
		this.high = high;
	}

}

