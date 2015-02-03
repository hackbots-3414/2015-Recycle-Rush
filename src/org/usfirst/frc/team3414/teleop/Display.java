package org.usfirst.frc.team3414.teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Display
{
	
    private static Display singleton = null;

    private Display()
    {
    }

    public static Display createInstance()
    {
        if (singleton == null)
        {
            singleton = new Display();
        }

        return singleton;
    }

    public static Display getInstance()
    {
        if (singleton == null)
        {
            throw new NullPointerException("Display hasn't been created yet");
        }

        return singleton;
    }
	
	
	
	
	
	
	private int time;
	
	boolean lo = false;
	
	public void putGameData() {
		SmartDashboard.putBoolean("Bool: " , lo);
		SmartDashboard.putNumber("Time: ", time);
	}
	public void putDiagnosticsData() {
		SmartDashboard.putBoolean("Bool: " , lo);
	}
	
	public void setTimerLog(int time) {
		this.time = time;
	}
	
}

