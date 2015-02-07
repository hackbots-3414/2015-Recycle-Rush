package org.usfirst.frc.team3414.sensors;

public interface ITimer {

	public long addListenerSec(ITimeListener listener, boolean continuous, long timeToExtendSec);
	
	public long addListenerMS(ITimeListener listener, boolean continuous, long timeToExtendMS);
	
	public void removeListener(long eventID);
	
	public double getTimeInMS();
	
	public double getTimeInSec();
	
}
