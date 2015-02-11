package org.usfirst.frc.team3414.sensors.timerListener;

import java.util.ArrayList;

public class TimerThread extends Thread
{
	private static TimerThread singleton = null;

    private TimerThread()
    {
    }

    public static TimerThread createInstance()
    {
        if (singleton == null)
        {
            singleton = new TimerThread();
        }

        return singleton;
    }

    public static TimerThread getInstance()
    {
        if (singleton == null)
        {
            throw new NullPointerException("TimerThread hasn't been created yet");
        }

        return singleton;
    }
	
	
	
	protected ArrayList<TimerListener> listeners = null;

	/**
	 * Add listener event in Milliseconds
	 * 
	 * @param listener
	 * @param milliseconds
	 * @param repeat
	 */
	public void addEventMS(ITimeListener listener, long milliseconds, boolean repeat)
	{
		if (this.listeners == null)
		{
			this.listeners = new ArrayList<TimerListener>();
		}
		this.listeners.add(new TimerListener(listener, new Timer(milliseconds), repeat));
	}

	/**
	 * Add listener event in Seconds
	 * 
	 * @param listener
	 * @param milliseconds
	 * @param repeat
	 */
	public void addEventSec(ITimeListener listener, int seconds, boolean repeat)
	{
		if (this.listeners == null)
		{
			this.listeners = new ArrayList<TimerListener>();
		}
		this.listeners.add(new TimerListener(listener, new Timer(seconds), repeat));
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

					this.listeners.remove(listener); // Remove this listener because it expired
					break;
				}
			}
			// Keep looping until we have no more listeners in the list or the thread is interrupted
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
		 * Run the Elapsed Response
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

		/**
		 * Check if object is repeated
		 * 
		 * @return
		 */
		public boolean isRepeted()
		{
			return this._rept;
		}

		/**
		 * Get the listener object
		 * 
		 * @return
		 */
		public ITimeListener getListener()
		{
			return this._listener;
		}

		/**
		 * Check the object's additional time
		 * 
		 * @return
		 */
		public long getTime()
		{
			return this._timr.getTime();
		}
	}
}
