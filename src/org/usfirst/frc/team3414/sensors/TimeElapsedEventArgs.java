package org.usfirst.frc.team3414.sensors;

public class TimeElapsedEventArgs
{

	private long endTime;

	private long timeEventID;

	private long startTime;

	public TimeElapsedEventArgs(long _timeEventID, long _startTime, long _endTime)
	{
		this.timeEventID = _timeEventID;
		this.startTime = _startTime;
		this.endTime = _endTime;
	}

	public long getEndTime()
	{
		return endTime;
	}

	public long getTimeEventID()
	{
		return timeEventID;
	}

	public long getStartTime()
	{
		return startTime;
	}

}
