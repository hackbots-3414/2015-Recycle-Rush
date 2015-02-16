package org.usfirst.frc.team3414.sensors;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.Timer;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class VirtualClock extends Thread implements IClock {

	private Timer timer;
	private long nextEventID = 0;
	private int sleepTime; 
	
	
	private Map<Long, TimeEventSubscription> subscriptions;
	
	protected VirtualClock(int sleeptime) {
		super();
		this.sleepTime = sleeptime;
		this.subscriptions = new Hashtable<Long, TimeEventSubscription>();
		timer = new Timer();
		timer.start();
		start();
	}
	
	protected VirtualClock() { 
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
	public long addListener(ITimeListener listener, long time) {
		return addListener(listener, time, false);
	}

	@Override
	public long addListener(ITimeListener listener, long timeout, boolean repeat) {
		long startTime = getTimeInMillis();
		subscriptions.put(nextEventID, new TimeEventSubscription(listener, startTime, timeout, repeat));
		return nextEventID++;
	}

	@Override
	public void removeListener(long distanceEventID) {
		subscriptions.remove(distanceEventID);
		
	}
	
	@Override
	public void run()
	{
		while(RobotStatus.isRunning())
		{
			List<Long> keys = new ArrayList<Long>(subscriptions.keySet());
			
			for(final long key : keys)
			{
				final TimeEventSubscription event = subscriptions.get(key);
				
				if(event != null)
				{
					final long currentTime = getTimeInMillis();
					
					if(currentTime >= event.getEndTime())
					{	
						// Lambda Runnable
						Runnable eventRunnable = new Runnable() {
							
							@Override
							public void run() {
								event.listener.timeEvent(new TimeEventArgs(key, currentTime));
							}
						};
						//Runnable eventRunnable = () -> { event.listener.distanceEvent(new DistanceEventArgs(key, distanceInCM)); };
						Thread eventTask = new Thread(eventRunnable);
						
						if(!event.repeat)
						{
							removeListener(key);
						}
						else
						{
							event.startTime = currentTime;
						}
						eventTask.start();
					}
				}
			}
			try {
				Thread.sleep(sleepTime);
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
		
		public TimeEventSubscription(ITimeListener listenter, long startTime, long timeout, boolean repeat) {
			super();
			this.listener = listenter;
			this.startTime = startTime;
			this.time = timeout;
			this.repeat = repeat;
			
		}
		long getEndTime()
		{
			return startTime + time;
		}
	}
}
