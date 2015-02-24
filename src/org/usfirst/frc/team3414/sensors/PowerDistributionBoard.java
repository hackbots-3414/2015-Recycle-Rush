package org.usfirst.frc.team3414.sensors;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * The Power Distribution Board event driven system. This class allows the
 * creation and removal of voltage and current listeners.
 * 
 * @author Ray, Rahul
 * @generated
 */

public class PowerDistributionBoard extends Thread implements
		IPowerEventHandler
{
	private long nextEventID = 0;
	private long updateInterval;
	private ExecutorService executor;

	Map<Long, PowerEventSubscription> currentListeners;
	Map<Long, PowerEventSubscription> voltageListeners;

	PowerDistributionPanel power = new PowerDistributionPanel();

	long eventID;

	/**
	 * 
	 * Adds a listener that triggers when the current drops below or goes above
	 * a certain value, in amps, on a specific channel.
	 * 
	 * @generated
	 * @ordered
	 */
	protected PowerDistributionBoard(int updateInterval)
	{
		super();
		this.currentListeners = new Hashtable<Long, PowerEventSubscription>();
		this.voltageListeners = new Hashtable<Long, PowerEventSubscription>();
		this.updateInterval = updateInterval;
		executor = Executors.newFixedThreadPool(2);
		start();
	}

	protected PowerDistributionBoard()
	{
		this(100);
	}

	@Override
	public long addCurrentListener(IPowerEventListener listener,
			PowerThreshold threshold, int channel)
	{
		return addCurrentListener(listener, threshold, channel, false);
	}

	@Override
	public long addCurrentListener(IPowerEventListener listener,
			PowerThreshold threshold, int channel, boolean repeat)
	{
		currentListeners.put(nextEventID, new PowerEventSubscription(listener,
				threshold, channel, repeat));
		return nextEventID++;
	}

	/**
	 * 
	 * Unsubscribes from a current listener, based on the input event ID.
	 * 
	 * @generated
	 * @ordered
	 */

	@Override
	public void removeCurrentListener(long eventId)
	{
		currentListeners.remove(eventId);
	}

	/**
	 * 
	 * Unsubscribes from a voltage listener, based on the event ID.
	 * 
	 * @generated
	 * @ordered
	 */

	@Override
	public void removeVoltageListener(long eventId)
	{
		voltageListeners.remove(eventId);
	}

	/**
	 * 
	 * Adds a listener which triggers an event when the voltage of a specific
	 * channel climbs above or dips below a certain voltage.
	 * 
	 * @generated
	 * @ordered
	 */

	@Override
	public long addVoltageListener(IPowerEventListener listener,
			PowerThreshold threshold, int channel)
	{
		return addVoltageListener(listener, threshold, channel, false);
	}

	/**
	 * 
	 * Adds a listener which triggers an event when the voltage of a specific
	 * channel climbs above or dips below a certain voltage.
	 * 
	 * @generated
	 * @ordered
	 */

	@Override
	public long addVoltageListener(IPowerEventListener listener,
			PowerThreshold threshold, int channel, boolean repeat)
	{
		voltageListeners.put(nextEventID, new PowerEventSubscription(listener,
				threshold, channel, repeat));

		return nextEventID++;
	}

	@Override
	public void run()
	{
		while (true)
		{
			List<Future<?>> futures = new ArrayList<Future<?>>();
			List<Long> keys = new ArrayList<Long>(currentListeners.keySet());
			for (final long key : keys)
			{
				final PowerEventSubscription event = currentListeners.get(key);
				if (event != null)
				{
					int channel = event.channel;
					double voltage = power.getVoltage();
					double watts = (power.getVoltage() / power
							.getCurrent(channel));
					double amps = power.getCurrent(channel);
					PowerThreshold threshold = event.threshold;
					if (amps > threshold.high || amps < threshold.low)
					{
						futures.add(executor.submit(() -> {
							event.listener.amperageEvent(new PowerEventArgs(
									channel, voltage, watts, amps, threshold));
						}));

						if (!event.repeat)
						{
							currentListeners.remove(key);
						}
					}

				}
			}
			keys = new ArrayList<Long>(voltageListeners.keySet());
			for (final long key : keys)
			{
				final PowerEventSubscription event = voltageListeners.get(key);
				if (voltageListeners.get(key) != null)
				{
					int channel = event.channel;
					double voltage = power.getVoltage();
					double watts = (power.getVoltage() / power
							.getCurrent(channel));
					double amps = power.getCurrent(channel);
					PowerThreshold threshold = event.threshold;

					if (voltage > threshold.high || voltage < threshold.low)
					{
						futures.add(executor.submit(() -> {
							event.listener.voltageEvent(new PowerEventArgs(
									channel, voltage, watts, amps, threshold));
						}));

						if (!event.repeat)
						{
							voltageListeners.remove(key);
						}
					}

				}
			}
			for (Future<?> f : futures)
			{
				while (!f.isDone())
				{
					try
					{
						Thread.sleep(10);
					} 
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						SmartDashboard.putString("DEBUG: ",
								"Distance Event Handler Failed to sleep");
					}
				}
			}
			try
			{
				Thread.sleep(updateInterval);
			} 
			catch (InterruptedException e)
			{
				SmartDashboard.putString("DEBUG: ",
						"Distance Event Handler Failed to sleep");
			}
		}

	}

	class PowerEventSubscription
	{
		IPowerEventListener listener;
		PowerThreshold threshold;
		int channel;
		boolean repeat;

		public PowerEventSubscription(IPowerEventListener listener,
				PowerThreshold threshold, int channel, boolean repeat)
		{
			super();
			this.listener = listener;
			this.threshold = threshold;
			this.channel = channel;
			this.repeat = repeat;
		}

	}

}
