package org.usfirst.frc.team3414.sensors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.wpi.first.wpilibj.Timer;

public class VirtualClock extends Thread implements ITimer {
	
	private Timer time;
	private MapClasher clash;
	private Long mapIncrementer = 0L;
	private TimeElapsedEventArgs runElapsed;
	
	Map<Long, MapClasher> mapListen = new HashMap<Long, MapClasher>();
	
	
	public VirtualClock() {
		time = new Timer();
		time.start();
		runElapsed = new TimeElapsedEventArgs();
	}
	
	public void run() {
		List <Long> eventIDs = new ArrayList<Long> (mapListen.keySet());
		for (long index : eventIDs) {
			MapClasher tempClash = mapListen.get(index);
			if(time.get() == (tempClash.getStartTime() + tempClash.getTimeToExtend())) {
				ITimeListener listen = clash.getITimeListener();
				listen.doWhenTimeElapsed();
			}
		}
	}

	@Override
	public long addListenerSec(ITimeListener listener, boolean continuous, long timeToExtendSec) {
		long startTime = (long)(time.get() * 1000);
		clash = new MapClasher(listener, continuous, startTime, (timeToExtendSec*1000));
		mapListen.put(mapIncrementer, clash);
		return mapIncrementer++;
	}
	
	@Override
	public long addListenerMS(ITimeListener listener, boolean continuous, long timeToExtendMS) {
		long startTime = (long)time.get();
		clash = new MapClasher(listener, continuous, startTime, timeToExtendMS);
		mapListen.put(mapIncrementer, clash);
		return mapIncrementer++;
	}

	@Override
	public void removeListener(long eventID) {
		mapListen.remove(eventID);
	}

	@Override
	public double getTimeInMS() {
		return (time.get() * 1000.0);
	}

	@Override
	public double getTimeInSec() {
		return time.get();
	}

}
