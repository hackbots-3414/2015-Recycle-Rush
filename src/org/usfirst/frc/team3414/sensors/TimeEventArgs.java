package org.usfirst.frc.team3414.sensors;


public class TimeEventArgs
{
	/**
	 */
	public long timeEventID;

	/**
	 */
	public long time;
	
	
	public TimeEventArgs(long timeEventID, long time) 
	{
		super();
		this.timeEventID = timeEventID;
		this.time = time;
	}
	
	/**
	 */
	public long getTimeEventID() 
	{
		return timeEventID;
	}
	
	/**
	 */
	public long getTimeInMillis()
	{
		return 0L;	
	}

	/**
	 */
	public long getTimeInMinutes()
	{
		return 0L;	
	}
	
	/**
	 */
	public long getTimeInSeconds()
	{
		return 0L;	
	}
}

