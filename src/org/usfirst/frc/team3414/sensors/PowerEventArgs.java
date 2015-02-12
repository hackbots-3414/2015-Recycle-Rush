package org.usfirst.frc.team3414.sensors;


/**
 * 
 * A data class containing all of the variables needed for an IPowerBoardListener event.
 * 
 * @generated
 */

public class PowerEventArgs
{
	
	public PowerEventArgs(int channel, double voltage, double watts, double amps, PowerThreshold threshold)
	{
		this.channel = channel;
		this.voltage = voltage;
		this.watts = watts;
		this.amps = amps;
		this.threshold = threshold;
	}
	
	/**
	 *
	 *The channel on the Power Distribution Panel where the measurements are being taken from.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public int channel;
	
	/**
	 * 
	 *The voltage of the channel.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public double voltage;
	
	/**
	 * 
	 *The wattage of the channel.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public double watts;
	
	/**
	 * 
	 *The amperage of the channel
	 * 
	 * @generated
	 * @ordered
	 */
	
	public double amps;
	
	/**
	 *
	 *The power threshold that triggers events
	 * 
	 * @generated
	 * @ordered
	 */
	
	public PowerThreshold threshold;
	
}

