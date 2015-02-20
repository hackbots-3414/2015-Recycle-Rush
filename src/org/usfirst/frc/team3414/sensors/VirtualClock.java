package org.usfirst.frc.team3414.sensors;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.usfirst.frc.team3414.robot.RobotStatus;
import org.usfirst.frc.team3414.sensors.PowerDistributionBoard.PowerEventSubscription;

import edu.wpi.first.wpilibj.Timer;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class VirtualClock extends Thread implements IClock {

	private Timer timer;
	private long nextEventID = 0;
	private long updateInterval;
	private ExecutorService executor;
	
	private Map<Long, TimeEventSubscription> timeListeners;
	
	protected VirtualClock(int updateInterval)
	    {
		super();
		this.timeListeners = new Hashtable<Long, TimeEventSubscription>();
		this.updateInterval = updateInterval;
		executor = Executors.newFixedThreadPool(2);
		start();
	    }

	    protected VirtualClock()
	    {
		this(50);
	    }


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	@Override
	public double getTimeInMinutes() {
		return getTimeInSeconds() / 60;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	@Override
	public double getTimeInSeconds() {
	    return timer.get();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	@Override
	public long getTimeInMillis() {
		return (long)(getTimeInSeconds() * 1000);
	}

	@Override
	public long addTimeListener(ITimeListener listener, long time) {
		return addTimeListener(listener, time, false);
	}

	@Override
	public long addTimeListener(ITimeListener listener, long time, boolean repeat) {
		long startTime = getTimeInMillis();
		timeListeners.put(nextEventID, new TimeEventSubscription(listener, startTime, time, repeat));
		return nextEventID++;
	}

	@Override
	public void removeListener(long timeEventID) {
		timeListeners.remove(timeEventID);
		
	}
	
	@Override
	public void run()
	{
		while(RobotStatus.isRunning())
		{
		    List<Future<?>> futures = new ArrayList<Future<?>>();
		    List<Long> keys = new ArrayList<Long>(timeListeners.keySet());
			for(final long key : keys)
			{
				final TimeEventSubscription event = timeListeners.get(key);
				
				if(event != null)
				{
					final long currentTime = getTimeInMillis();
					
					if(currentTime >= event.getEndTime())
					{	
					    futures.add(executor.submit(() -> {
						    event.listener.timeEvent(new TimeEventArgs(key, currentTime));
						}));
						
						if(!event.repeat)
						{
							removeListener(key);
						}
						else
						{
							event.startTime = currentTime;
						}
					}
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	class TimeEventSubscription {
		ITimeListener listener;
		long startTime;
		long endTime;
		long time;
		boolean repeat;
		
		public TimeEventSubscription(ITimeListener listener, long startTime, long time, boolean repeat) {
			super();
			this.listener = listener;
			this.startTime = startTime;
			this.time = time;
			this.repeat = repeat;
			
		}
		long getEndTime()
		{
			return startTime + time;
		}
	}
}
