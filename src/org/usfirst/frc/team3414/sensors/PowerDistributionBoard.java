package org.usfirst.frc.team3414.sensors;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * 
 * The Power Distribution Board event driven system. This class allows the creation and removal of voltage and current listeners.
 * 
 * @author Ray, Rahul
 * @generated
 */

public class PowerDistributionBoard implements IPowerEventHandler, Runnable
{

	Map<Long, PowerListenerInformation> currentListeners = new HashMap<>();
	Map<Long, PowerListenerInformation> voltageListeners = new HashMap<>();

	PowerDistributionPanel power = new PowerDistributionPanel();

	long eventID;
	
	public PowerDistributionBoard()
	{

	}

	/**
	 * 
	 * Adds a listener that triggers when the current drops below or goes above a certain value, in amps, on a specific channel.
	 * 
	 * @generated
	 * @ordered
	 */

	@Override
	public long addCurrentListener(IPowerBoardListener listener,
			PowerThreshold threshold, int channel)
	{

		PowerListenerInformation power = new PowerListenerInformation(channel,
				threshold, listener);

		eventID++;

		currentListeners.put(eventID, power);

		return eventID;
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
	 * Adds a listener which triggers an event when the voltage of a specific channel climbs above or dips below a certain voltage.
	 * 
	 * @generated
	 * @ordered
	 */

	@Override
	public long addVoltageListener(IPowerBoardListener listener,
			PowerThreshold threshold, int channel)
	{

		PowerListenerInformation power = new PowerListenerInformation(channel,
				threshold, listener);

		eventID++;

		currentListeners.put(eventID, power);

		return eventID;
	}

	@Override
	public void run()
	{

		int channel;
		double voltage;
		double watts;
		double amps;
		PowerThreshold threshold;

		while (true)
		{
			for (int i = 0; i < eventID; i++)
			{
				if (currentListeners.get(i) != null)
				{
					channel = currentListeners.get(i).channel;
					voltage = power.getVoltage();
					watts = (power.getVoltage() / power.getCurrent(channel));
					amps = power.getCurrent(channel);
					threshold = currentListeners.get(i).threshold;

					if (power.getCurrent(currentListeners.get(i).channel) > currentListeners
							.get(i).threshold.high)
					{
						currentListeners.get(i).listener
								.amperageEvent(new PowerEventArgs(channel,
										voltage, watts, amps, threshold));
						
						// spawns a new thread
					}

					if (power.getCurrent(currentListeners.get(i).channel) < currentListeners
							.get(i).threshold.low)
					{
						currentListeners.get(i).listener
								.amperageEvent(new PowerEventArgs(channel,
										voltage, watts, amps, threshold));
						
						// spawns a new thread
					}
				}

				if (voltageListeners.get(i) != null)
				{
					channel = voltageListeners.get(i).channel;
					voltage = power.getVoltage();
					watts = (power.getVoltage() / power.getCurrent(channel));
					amps = power.getCurrent(channel);
					threshold = voltageListeners.get(i).threshold;

					if (power.getVoltage() > voltageListeners.get(i).threshold.high)
					{
						voltageListeners.get(i).listener
								.voltageEvent(new PowerEventArgs(channel,
										voltage, watts, amps, threshold));
						
						// spawns a new thread
					}

					if (power.getVoltage() < voltageListeners.get(i).threshold.low)
					{
						voltageListeners.get(i).listener
								.voltageEvent(new PowerEventArgs(channel,
										voltage, watts, amps, threshold));
						
						// spawns a new thread
					}
				}
			}
		}

	}

}
