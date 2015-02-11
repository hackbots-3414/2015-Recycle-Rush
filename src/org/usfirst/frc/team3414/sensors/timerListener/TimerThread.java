package org.usfirst.frc.team3414.sensors.timerListener;

import java.util.ArrayList;

public class TimerThread extends Thread
{
	protected ArrayList<TimerListener> listeners = null;// new
														// ArrayList<TimerListener>();

	public TimerThread()
	{
	}

	/**
	 * Add listener events
	 * 
	 * @param listener
	 * @param milliseconds
	 */
	public void addEventMS(ITimeListener listener, long milliseconds, boolean repeat)
	{
		if (this.listeners == null)
		{
			this.listeners = new ArrayList<TimerListener>();
		}
		this.listeners.add(new TimerListener(listener, new Timer(milliseconds), repeat));
	}

	public void addEventSec(ITimeListener listener, long milliseconds, boolean repeat)
	{
		if (this.listeners == null)
		{
			this.listeners = new ArrayList<TimerListener>();
		}
		this.listeners.add(new TimerListener(listener, new Timer(milliseconds), repeat));
	}

	/**
	 * This is the thread runner
	 */
	@Override
	public void run()
	{
		do
		{
			for (TimerListener listener : this.listeners)
			{
				if (listener.isExpired()) // check for expired timers
				{
					listener.signalExpired(); // Signal the object listening to
												// know time has expired
					if (listener.isRepeted())
					{
						this.listeners.add(new TimerListener(listener.getListener(), new Timer(listener.getTime()), listener.isRepeted()));
					}
					
					this.listeners.remove(listener); // Remove this listener
					// because it
					// expired
					break;
				}
			}
			// Keep looping until we have no more listeners in the list or the
			// thread is interrupted
		} while (!Thread.interrupted() && this.listeners.size() > 0);
	}

	/**
	 * Define an inner class to be used only by TimerThread
	 * 
	 * @author Dean
	 *
	 */
	protected class TimerListener
	{
		private ITimeListener _listener;
		private Timer _timr;
		private boolean _rept;

		/**
		 * Constructor to add the listener and the timer
		 * 
		 * @param listener
		 * @param timr
		 */
		public TimerListener(ITimeListener listener, Timer timr, boolean rept)
		{
			this._listener = listener;
			this._timr = timr;
			this._rept = rept;
		}

		/**
		 * Get the listener object
		 * 
		 * @return
		 */
		public void signalExpired()
		{
			this._listener.doWhenTimeElapsed();
		}

		/**
		 * Check for an expired timer
		 * 
		 * @return
		 */
		public boolean isExpired()
		{
			return this._timr.isExpired();
		}

		public boolean isRepeted()
		{
			return this._rept;
		}
		
		public ITimeListener getListener()
		{
			return this._listener;
		}
		
		public long getTime()
		{
			return this._timr.getTime();
		}
	}
}
