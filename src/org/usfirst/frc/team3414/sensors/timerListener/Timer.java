package org.usfirst.frc.team3414.sensors.timerListener;

public class Timer
{
	private long endMilliseconds = 0;
	private long addTime = 0;
	
	/**
	 * 
	 * @param milliseconds - number of milliseconds this timer should run for
	 */
	public Timer(long milliseconds)
	{
		// Establish when the timer should expire
		this.addTime = milliseconds;
		this.endMilliseconds = System.currentTimeMillis() + this.addTime;
	}
	
	public Timer(int seconds)
	{
		// Establish when the timer should expire
		this.addTime = (1000 * seconds);
		this.endMilliseconds = System.currentTimeMillis() + this.addTime;
	}
	
	/**
	 * Check for an expired timer
	 * @return
	 */
	public boolean isExpired()
	{
		return this.endMilliseconds <= System.currentTimeMillis();
	}
	
	public long getTime(){
		return this.addTime;
	}
}
