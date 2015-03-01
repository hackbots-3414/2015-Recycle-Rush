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
	
	private void fireEvent(ButtonEventSubscription event, List<Future<?>> futures, long key)
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

	@Override
	public void run()
	{
		List<Future<?>> futures = new ArrayList<Future<?>>();
		List<Boolean> previousValues = new ArrayList<>();
		
		while (RobotStatus.isRunning())
		{
			List<Long> keys = new ArrayList<Long>(buttonListeners.keySet());
			if (!override)
			{
				for (final long key : keys)
				{
					final ButtonEventSubscription event = buttonListeners.get(key);
					
					if (event != null)
					{
						// Pressed
						if (joy.getButton(event.button) && !event.buttonRestrict)
						{
							event.buttonState = ButtonStates.PRESSED;
							fireEvent(event, futures, key);
						}
						
						// Released
						if(previousValues.get(event.button.getValue()) && !joy.getButton(event.button)) // If previous value was true and current value is false (AKA. Released)
						{
							event.buttonState = ButtonStates.RELEASED;
							fireEvent(event, futures, key);
						}
						
						previousValues.add(event.button.getValue(), (joy.getButton(event.button)));
						
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
		boolean buttonRestrict = false;
		ButtonStates buttonState = null;

		public ButtonEventSubscription(IButtonListener listener, JoystickButtons button, boolean repeat, ButtonStates whenToFire)
		{
			super();
			this.listener = listener;
			this.button = button;
			this.repeat = repeat;
		}
	}
}
