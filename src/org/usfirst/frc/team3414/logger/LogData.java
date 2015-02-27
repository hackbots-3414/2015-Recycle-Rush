package org.usfirst.frc.team3414.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class LogData
{

	private final String logFile = "/home/lvuser/logger3414.log";
	private static LogData logData = null;
	private String cr = System.getProperty("line.separator");
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss - ");
	
	public static LogData getInstance()
	{
		if (logData == null)
		{
			logData = new LogData();
		}
		return logData;
	}

	/**
	 * Record text to Log located in "/home/lvuser/logger3414.log"
	 * @param buffer is a string of text to be logged
	 */
	public synchronized void record(String buffer)
	{
		try
		{
			write(buffer);
		} catch (IOException e)
		{
			System.err.println("Error writing to log " + e.getMessage());
		}
	}

	/**
	 * write to logfile on roborio
	 * 
	 * @param buffer
	 * @throws IOException
	 */
	private void write(String buffer) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(new File(this.logFile)));
		out.append(getTimeStamp()).append(buffer).append(cr);
		out.close();
	}
	
	private String getTimeStamp()
	{
        return this.dateFormater.format(new GregorianCalendar().getTime());
	}
}
