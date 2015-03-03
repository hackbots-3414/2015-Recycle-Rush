package org.usfirst.frc.team3414.teleop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private IHaveButtons joy;
	public boolean override = false;

	protected ButtonEventHandler(IHaveButtons joystick, int updateInterval)
	{
		buttonListeners = new HashMap<>();
		joy = joystick;
		this.updateInterval = updateInterval;
		executor = Executors.newFixedThreadPool(1); // Only respond to one
													// joystick button press at
													// a time
		start();
	}

	protected ButtonEventHandler(IHaveButtons joystick)
	{
		this(joystick, 75);
	}

	@Override
	public long addButtonListener(IButtonListener listener, JoystickButtons button)
	{
		return addButtonListener(listener, button, false);
	}

	@Override
	public long addButtonListener(IButtonListener listener, JoystickButtons button, boolean repeat, ButtonStates whenToFire)
	{
		buttonListeners.put(nextEventID, new ButtonEventSubscription(listener, button, repeat, whenToFire));
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
		List<Future<?>> futures = new ArrayList<Future<?>>();
		Map<Integer, Boolean> previousValues = new HashMap<>();

		for (int i = 0; i < 100; i++)
		{
			previousValues.put(i, false);
		}

		while (RobotStatus.isRunning())
		{
			List<Long> keys = new ArrayList<Long>(buttonListeners.keySet());
			if (!override)
			{
				for (final long key : keys)
				{
					final ButtonEventSubscription event = buttonListeners.get(key);

					if (event != null && event.button != null)
					{
						// Pressed
						if ((!previousValues.get(event.button.getValue()) && joy.getButton(event.button)))
						{
							event.buttonState = ButtonStates.PRESSED;

							if (event.whenToFire == ButtonStates.PRESSED)
							{
								futures.add(executor.submit(() -> {
									event.listener.buttonEvent(new ButtonEventArgs(key, event.button, ButtonStates.PRESSED));
								}));
								
								event.future = futures.get(futures.size()-1);
							}
						}

						// Released
						if ((previousValues.get(event.button.getValue()) && !joy.getButton(event.button)))
						{
							event.buttonState = ButtonStates.RELEASED;

							event.future.cancel(true);
							
							if (event.whenToFire == ButtonStates.RELEASED)
							{	
								futures.add(executor.submit(() -> {
									event.listener.buttonEvent(new ButtonEventArgs(key, event.button, ButtonStates.RELEASED));
								}));
							}
						}

						if (!event.repeat)
						{
							buttonListeners.remove(key);
						} else
						{

						}

						previousValues.put(event.button.getValue(), (joy.getButton(event.button)));

						// if (!joy.getButton(event.button) &&
						// event.buttonRestrict)
						// {
						// event.buttonRestrict = false;
						// }
					}
				}

			} else
			{
				for (Future<?> f : futures)
				{
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

	@Override
	public long addButtonListener(IButtonListener listener, JoystickButtons button, boolean repeat)
	{
		return addButtonListener(listener, button, repeat, ButtonStates.PRESSED);
	}

	class ButtonEventSubscription
	{
		IButtonListener listener;
		JoystickButtons button;
		boolean repeat;
		ButtonStates buttonState = null;
		ButtonStates whenToFire;
		
		Future<?> future;

		public ButtonEventSubscription(IButtonListener listener, JoystickButtons button, boolean repeat, ButtonStates whenToFire)
		{
			super();
			this.listener = listener;
			this.button = button;
			this.repeat = repeat;
			this.whenToFire = whenToFire;
		}
	}
}
