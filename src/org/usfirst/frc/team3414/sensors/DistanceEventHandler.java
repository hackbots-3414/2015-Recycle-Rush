package org.usfirst.frc.team3414.sensors;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistanceEventHandler extends Thread implements IDistanceEventHandler 
{
	
	private long nextEventID = 0;
	private long updateInterval;
	private ExecutorService executor; 
	
	private Map<Long, DistanceEventSubscription> subscriptions;
	
	public DistanceEventHandler(int updateInterval) {
		super();
		this.subscriptions = new Hashtable<Long, DistanceEventSubscription>();
		this.updateInterval = updateInterval;
		executor = Executors.newFixedThreadPool(2);
		start();
	}
	
	public DistanceEventHandler() {
		this(100);
	}

	@Override
	public long addListener(IMeasureDistanceListener listener, IMeasureDistance sensor, Range distance) 
	{
		return addListener(listener, sensor, distance, false);
	}

	@Override
	public long addListener(IMeasureDistanceListener listener, IMeasureDistance sensor, Range distance, boolean repeat) 
	{
		subscriptions.put(nextEventID, new DistanceEventSubscription(listener, sensor, distance, repeat));
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
			List<Future<?>> futures = new ArrayList<Future<?>>();
			
			for(final long key : keys)
			{
				final DistanceEventSubscription event = subscriptions.get(key);
				
				if(event != null)
				{
					final double distanceInCM = event.sensor.getCm();
					
					if(distanceInCM >= event.distance.getMinimum() && distanceInCM <= event.distance.getMaximum())
					{	
						// Lambda Runnable
//						Runnable eventRunnable = new Runnable() {
//							
//							@Override
//							public void run() {
//								event.listener.distanceEvent(new DistanceEventArgs(key, distanceInCM));
//								
//							}
//						};
						futures.add(
								executor.submit(() -> { 
									event.listener.distanceEvent(new DistanceEventArgs(key, distanceInCM));
								})
						);
						
						if(!event.repeat)
						{
							removeListener(key);
						}
					}
				}
			}
			for(Future<?> f : futures)
			{
				while(!f.isDone())
				{
					try
					{
						Thread.sleep(10);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						SmartDashboard.putString("DEBUG: ", "Distance Event Handler Failed to sleep");
					}
				}
			}
			try
			{
				Thread.sleep(updateInterval);
			} catch (InterruptedException e)
			{
				SmartDashboard.putString("DEBUG: ", "Distance Event Handler Failed to sleep");
			}
		}
		
		
	}
	
	private class DistanceEventSubscription
	{
		IMeasureDistanceListener listener;
		IMeasureDistance sensor;
		Range distance;
		boolean repeat;
		
		
		public DistanceEventSubscription(IMeasureDistanceListener listener, IMeasureDistance sensor, Range distance, boolean repeat) 
		{
			super();
			this.listener = listener;
			this.sensor = sensor;
			this.distance = distance;
			this.repeat = repeat;
		}
		
		
		
	}
	
}