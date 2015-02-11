package org.usfirst.frc.team3414.sensors.timerListener;

public class VirtualClock
{
	
	public VirtualClock()
	{
	}
	
	public void addEvents()
	{

		TimerThread timer = TimerThread.createInstance();
		//timer.addEvent(new JustAnotherWorker(), 150);
		//timer.addEvent(event Extension, time arg);
		timer.addEventMS(new ExampleClassUsingTimerListener(), 30, false);
		timer.start();		// Start the thread
	}

}

/*
package org.usfirst.frc.team3414.sensors.timerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.wpi.first.wpilibj.Timer;

public class VirtualClock extends Thread implements ITimer
{

	private Timer time;
	private MapClasher clash;
	private Long mapIncrementer = 0L;
	private TimeElapsedEventArgs runElapsed;

	private Map<Long, MapClasher> mapListen = new HashMap<Long, MapClasher>();

	public VirtualClock(TimeElapsedEventArgs _runElapsed)
	{
		this.time = new Timer();
		this.time.start();
		this.runElapsed = _runElapsed;
	}

	public void run()
	{
		List<Long> eventIDs = new ArrayList<Long>(mapListen.keySet());
		for (long index : eventIDs)
		{
			MapClasher tempClash = mapListen.get(index);
			if (time.get() == (tempClash.getStartTime() + tempClash.getTimeToExtend()))
			{
				ITimeListener listen = tempClash.getITimeListener();
				listen.doWhenTimeElapsed();
				//TODO: Finish Continuous operation
				if (tempClash.getContinuous())
				{
					tempClash.setStartTime((long)time.get());
					mapListen.put(index, tempClash);
				}
			}
		}
	}

	@Override
	/**
	 * @param ITimeListener- Class (which implements ITimeListener) which MUST contain doWhenTimeElapsed() method
	 * @param boolean- whether or not timer should continue to repeat
	 * @param timeToExtendSec- the number of seconds the timer should extend before executing
	 * @return long- returns the ID of the Timer; useful for removing the Timer
	 *
	public long addListenerSec(ITimeListener listener, boolean continuous, long timeToExtendSec)
	{
		long startTime = (long) (time.get() * 1000);
		clash = new MapClasher(listener, continuous, startTime, (timeToExtendSec * 1000));
		mapListen.put(mapIncrementer, clash);
		return mapIncrementer++;
	}

	@Override
	public long addListenerMS(ITimeListener listener, boolean continuous, long timeToExtendMS)
	{
		long startTime = (long) time.get();
		clash = new MapClasher(listener, continuous, startTime, timeToExtendMS);
		mapListen.put(mapIncrementer, clash);
		return mapIncrementer++;
	}

	@Override
	public void removeListener(long eventID)
	{
		mapListen.remove(eventID);
	}

	@Override
	public double getTimeInMS()
	{
		return (time.get() * 1000.0);
	}

	@Override
	public double getTimeInSec()
	{
		return time.get();
	}

	
}
*/
