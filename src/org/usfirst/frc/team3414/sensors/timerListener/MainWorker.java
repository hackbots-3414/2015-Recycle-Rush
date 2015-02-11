package org.usfirst.frc.team3414.sensors.timerListener;

import listener.TimerEventListener;
import listener.TimerThread;

public class MainWorker
{
	
	public MainWorker()
	{
	}
	
	public static void main(String[] args)
	{
		// Create a single timer and add 5 events to watch for
		TimerThread timer = new TimerThread();
		//timer.addEvent(new JustAnotherWorker(), 150);
		//timer.addEvent(event Extension, time arg);
		timer.start();		// Start the thread
	}

}
