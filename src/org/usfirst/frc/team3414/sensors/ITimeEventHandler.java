package org.usfirst.frc.team3414.sensors;

public interface ITimeEventHandler 
{
	public long addListener(ITimeListener listener, long time);
	
	public long addListener(ITimeListener listener, long time, boolean repeat);
	
	public void removeListener(long timeEventID);
}
