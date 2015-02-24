package org.usfirst.frc.team3414.sensors;

public interface ITimeEventHandler 
{
	public long addTimeListener(ITimeListener listener, long time);
	
	public long addTimeListener(ITimeListener listener, long time, boolean repeat);
	
	public void removeListener(long timeEventID);
}
