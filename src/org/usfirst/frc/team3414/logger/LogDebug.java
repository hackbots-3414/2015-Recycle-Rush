package org.usfirst.frc.team3414.logger;

import java.io.IOException;

public class LogDebug extends LogData implements IRecordData
{
	private boolean isDebugOn = false;
	
	private static LogDebug logDebug = null;

	public static LogDebug getInstance()
	{
		if (logDebug == null)
		{
			logDebug = new LogDebug();
		}
		return logDebug;
	}

	/**
	 * Record text to Log located in "/home/lvuser/logger3414.log"
	 * @param buffer is a string of text to be logged
	 */
	public synchronized void record(String buffer)
	{
		if (isDebugOn)
		{
			try
			{
				write("DEBUG - " + buffer);
			} catch (IOException e)
			{
				System.err.println("Error writing to log " + e.getMessage());
			}
		}
	}
}
