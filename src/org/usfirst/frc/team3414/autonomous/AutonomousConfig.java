package org.usfirst.frc.team3414.autonomous;

public class AutonomousConfig 
{	
	private static AutonomousConfig singleton;
	
	private IDriverAssist driveAssist;

	private AutonomousConfig()
	{
		super();
	}
	
	public static AutonomousConfig getInstance()
	{
		if(singleton == null)
		{
			singleton = new AutonomousConfig();
		}
		return singleton;
	}

	public IDriverAssist getDriveAssist()
	{
		return driveAssist;
	}	
}
