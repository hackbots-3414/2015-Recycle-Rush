package org.usfirst.frc.team3414.sensors;

class PowerListenerInformation {

	protected PowerListenerInformation(int channel, PowerThreshold threshold, IPowerBoardListener listener)
	{
		this.listener = listener;
		this.threshold = threshold;
		this.channel = channel;
	}
	
	/**
	 * The listener object of the data class.
	 */
	IPowerBoardListener listener;
	
	/**
	 * The channel on which we are measuring.
	 */
	int channel;
	
	/**
	 * The voltage or current threshold data.
	 */
	PowerThreshold threshold;
}
