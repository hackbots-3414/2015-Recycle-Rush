package org.usfirst.frc.team3414.teleop;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.sensors.IClock;
import org.usfirst.frc.team3414.sensors.ITimeListener;
import org.usfirst.frc.team3414.sensors.SensorConfig;
import org.usfirst.frc.team3414.sensors.TimeEventArgs;

public class Display
{

	long gameDataEventID;
	long diagnosticsDataEventID;
	long driveDataEventID;
	long sensorDataEventID;
	int refreshRateMillis;
	
	SensorConfig sensors = SensorConfig.getInstance();
	ActuatorConfig actuators = ActuatorConfig.getInstance();
	
	IClock clock = sensors.getClock();
	
	private static Display singleton = null;

	private Display()
	{
		
	}

	public static Display getInstance()
	{
		if(singleton == null)
		{
			singleton = new Display();
		}
		return singleton;
	}
	
	public void enableGameData()
	{
		gameDataEventID = clock.addTimeListener(new ITimeListener()
		{

			@Override
			public void timeEvent(TimeEventArgs timeEvent)
			{
				if(timeEvent.getTimeEventID() == gameDataEventID)
				{
					// Smart dashboard calls to put game data here
				}
			}
			
		}, refreshRateMillis);
	}
	
	public void disableGameData()
	{
		clock.removeListener(gameDataEventID);
	}

	public void enableDiagnosticsData()
	{
		diagnosticsDataEventID = clock.addTimeListener(new ITimeListener()
		{

			@Override
			public void timeEvent(TimeEventArgs timeEvent)
			{
				if(timeEvent.getTimeEventID() == gameDataEventID)
				{
					// Smart dashboard calls to put diagnostics data here
				}
			}
			
		}, refreshRateMillis);
	}
	
	public void disableDiagnosticsData()
	{
		clock.removeListener(gameDataEventID);
	}

	public void enableDriveData()
	{
		driveDataEventID = clock.addTimeListener(new ITimeListener()
		{

			@Override
			public void timeEvent(TimeEventArgs timeEvent)
			{
				if(timeEvent.getTimeEventID() == gameDataEventID)
				{
					// Smart dashboard calls to put drive data here
				}
			}
			
		}, refreshRateMillis);
	}

	public void disableDriveData()
	{
		clock.removeListener(driveDataEventID);
	}
	
	public void enableSensorData()
	{
		sensorDataEventID = clock.addTimeListener(new ITimeListener()
		{

			@Override
			public void timeEvent(TimeEventArgs timeEvent)
			{
				if(timeEvent.getTimeEventID() == gameDataEventID)
				{
					// display sensor data here
				}
			}
			
		}, refreshRateMillis);
	}
	
	public void disableSensorData()
	{
		clock.removeListener(sensorDataEventID);
	}
	
	public void setRefreshRate(int refreshRateMillis)
	{
		this.refreshRateMillis = refreshRateMillis;
	}

}
