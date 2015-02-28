package org.usfirst.frc.team3414.teleop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ButtonEventHandler extends Thread implements IButtonEventHandler
{
	private ExecutorService executor;
	private long nextEventID = 0;
	private Map<Long, ButtonEventSubscription> buttonListeners;
	private int updateInterval;
	private IJoystick joy;
	public boolean override = false;

	protected ButtonEventHandler(IJoystick joystick, int updateInterval)
	{
		buttonListeners = new HashMap<>();
		joy = joystick;
		this.updateInterval = updateInterval;
		executor = Executors.newFixedThreadPool(1); // Only respond to one
													// joystick button press at
													// a time
		start();
	}

	protected ButtonEventHandler(IJoystick joystick)
	{
		this(joystick, 75);
	}

	@Override
	public long addButtonListener(IButtonListener listener, JoystickButtons button)
	{
		return addButtonListener(listener, button, false);
	}

	@Override
	public long addButtonListener(IButtonListener listener, JoystickButtons button, boolean repeat)
	{
		buttonListeners.put(nextEventID, new ButtonEventSubscription(listener, button, repeat));
		return nextEventID++;
	}

	@Override
	public void removeListener(long joystickEventID)
	{
		buttonListeners.remove(joystickEventID);
	}

	@Override
	public void run()
	{
		while (RobotStatus.isRunning())
		{
			List<Future<?>> futures = new ArrayList<Future<?>>();
			List<Long> keys = new ArrayList<Long>(buttonListeners.keySet());
			if (!override)
			{
				for (final long key : keys)
				{
					final ButtonEventSubscription event = buttonListeners.get(key);
					if (event != null)
					{
						if (joy.getButton(event.button) && !event.buttonRestrict)
						{
							event.buttonRestrict = true;
							futures.add(executor.submit(() -> {
								event.listener.buttonEvent(new ButtonEventArgs(key, event.button));
							}));

							if (!event.repeat)
							{
								buttonListeners.remove(key);
							} else
							{

							}
						}
						if (!joy.getButton(event.button) && event.buttonRestrict)
						{
							event.buttonRestrict = false;
						}
					}
				}
			} else
			{
				for (Future<?> f : futures)
				{
					SmartDashboard.putBoolean("Is Cancelled", f.isCancelled());
					if (!f.isCancelled())
					{
						f.cancel(true);
					}

				}
				futures.clear();
				override = false;
			}

			try
			{
				Thread.sleep(updateInterval);
			} catch (InterruptedException e)
			{
				SmartDashboard.putString("DEBUG: ", "Button Failed to sleep");
			}
		}
	}

	public void clearQueue()
	{
		override = true;
	}

	class ButtonEventSubscription
	{
		IButtonListener listener;
		JoystickButtons button;
		boolean repeat;
		boolean buttonRestrict = false;

		public ButtonEventSubscription(IButtonListener listener, JoystickButtons button, boolean repeat)
		{
			super();
			this.listener = listener;
			this.button = button;
			this.repeat = repeat;
		}
	}
}
