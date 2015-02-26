package org.usfirst.frc.team3414.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Locale;

public class LogData
{

	private final String logFile = "";
	private static LogData logData = null;
	private String cr = System.getProperty("line.separator");
	private StringBuilder sbr = new StringBuilder();
	private Formatter formatter = new Formatter(sbr, Locale.US);
	
	public static LogData getInstance()
	{
		if (logData == null)
		{
			logData = new LogData();
		}
		return logData;
	}

	/**
	 * record text to Log
	 * @param buffer
	 */
	public void record(String buffer)
	{
		write(buffer);
	}

	/**
	 * write to logfile on roborio
	 * 
	 * @param buffer
	 * @throws IOException
	 */
	private synchronized void write(String buffer) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(new File(this.logFile)));
		out.append(getTimeStamp()).append(buffer).append(cr);
		out.close();
	}
	
	private String getTimeStamp()
	{
		Calendar cal = new GregorianCalendar();
		String date = String.format("%1$tmm %1$tEE,%1$tYY %1$tHH,%1$tMM,%1$tSS.S", cal.get(Calendar.YEAR), cal.get(Calendar.DAY_OF_MONTH), 
				cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND));
	}
}
