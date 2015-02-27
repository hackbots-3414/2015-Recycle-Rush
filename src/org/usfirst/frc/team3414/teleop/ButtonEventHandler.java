package org.usfirst.frc.team3414.teleop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ButtonEventHandler implements IButtonEventHandler
{
	private ExecutorService executor;
	private long nextEventID = 0;
	private Map<Long, ButtonEventSubscription> buttonListeners;
	int updateInterval;
	IJoystick joy;

	protected ButtonEventHandler(IJoystick joystick, int updateInterval)
	{
		joy = joystick;
		this.updateInterval = updateInterval;
		executor = Executors.newFixedThreadPool(1); // Only respond to one
													// joystick button press at
													// a time
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

	public void run()
	{
		while (RobotStatus.isRunning())
		{
			List<Future<?>> futures = new ArrayList<Future<?>>();
			List<Long> keys = new ArrayList<Long>(buttonListeners.keySet());
			for (final long key : keys)
			{
				final ButtonEventSubscription event = buttonListeners.get(key);
				if (event != null)
				{
					boolean prevButtonState = joy.getButton(event.button);
					if (!joy.getButton(event.button) && prevButtonState) // When
																			// going
																			// from
																			// true
																			// to
																			// false
					{
						futures.add(executor.submit(() ->
						{
							event.listener.buttonEvent(new ButtonEventArgs(key, event.button));
						}));
						if (!event.repeat)
						{
							buttonListeners.remove(key);
						} else
						{

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
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						SmartDashboard.putString("DEBUG: ", "Button Failed to sleep");
					}
				}
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

	class ButtonEventSubscription
	{
		IButtonListener listener;
		JoystickButtons button;
		boolean repeat;

		public ButtonEventSubscription(IButtonListener listener, JoystickButtons button, boolean repeat)
		{
			super();
			this.listener = listener;
			this.button = button;
			this.repeat = repeat;
		}
	}
}
