package org.usfirst.frc.team3414.sensors;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.usfirst.frc.team3414.robot.RobotStatus;

public class DistanceEventHandler extends Thread implements IDistanceEventHandler 
{
	
	private long nextEventID = 0;

	private Map<Long, EventSubscription> subscriptions;
	
	public DistanceEventHandler() {
		super();
		this.subscriptions = new Hashtable<Long, EventSubscription>();
	}

	@Override
	public long addListener(IMeasureDistanceListener listener, IMeasureDistance sensor, Range distance) 
	{
		return addListener(listener, sensor, distance, false);
	}

	@Override
	public long addListener(IMeasureDistanceListener listener, IMeasureDistance sensor, Range distance, boolean repeat) 
	{
		subscriptions.put(nextEventID, new EventSubscription(listener, sensor, distance, repeat));
		return nextEventID++;
	}

	@Override
	public void removeListener(long distanceEventID) 
	{
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
				final EventSubscription event = subscriptions.get(key);
				
				if(event != null)
				{
					final double distanceInCM = event.sensor.getCm();
					
					if(distanceInCM >= event.distance.getMinimum() && distanceInCM <= event.distance.getMaximum())
					{	
						// Lambda Runnable
						Runnable eventRunnable = new Runnable() {
							
							@Override
							public void run() {
								event.listener.distanceEvent(new DistanceEventArgs(key, distanceInCM));
								
							}
						};
						//Runnable eventRunnable = () -> { event.listener.distanceEvent(new DistanceEventArgs(key, distanceInCM)); };
						Thread eventTask = new Thread(eventRunnable);
						
						if(!event.repeat)
						{
							removeListener(key);
						}
						eventTask.start();
					}
				}
			}
		}
	}
	
	private class EventSubscription
	{
		IMeasureDistanceListener listener;
		IMeasureDistance sensor;
		Range distance;
		boolean repeat;
		
		
		public EventSubscription(IMeasureDistanceListener listener, IMeasureDistance sensor, Range distance, boolean repeat) 
		{
			super();
			this.listener = listener;
			this.sensor = sensor;
			this.distance = distance;
			this.repeat = repeat;
		}
		
		
		
	}
	
}