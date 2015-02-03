package org.usfirst.frc.team3414.sensors;

public class MapClasher {

	ITimeListener listener;
	boolean continuous;
	long startTime;
	long timeToExtend;
	
	public MapClasher(ITimeListener listener, boolean continuous, long startTime, long timeToExtend) {
		this.listener = listener;
		this.continuous = continuous;
		this.startTime = startTime;
		this.timeToExtend = timeToExtend;
	}
	
	public ITimeListener getITimeListener() {
		return listener;
	}
	
	public boolean getContinuous() {
		return continuous;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public long getTimeToExtend() {
		return timeToExtend;
	}
	
	
	
}
