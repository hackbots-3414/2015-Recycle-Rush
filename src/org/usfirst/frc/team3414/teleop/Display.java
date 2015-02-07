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
	
	public void setDriveData(double getGyroRate, double getJoyMagnitude, double getJoyDirection, double getJoyDirectionAdjustWithGyro, double getJoyTwist){
		/*
    	SmartDashboard.putNumber("Gyro Value (rate)", gyro.getRate());
        SmartDashboard.putNumber("Joystick Value Magnitude", joystick.getMagnitude());
        SmartDashboard.putNumber("Joystick Value Direction", joystick.getDirectionDegrees());
        SmartDashboard.putNumber("Joystick Direction - gyro rate", angle-(gyro.getRate()*Kp));
        SmartDashboard.putNumber("Joystick Value Twist", joystick.getTwist());
        */
	}
	
	public void setGyroData(double gyroAngle, double gyroRate) {
		/*
    	SmartDashboard.putNumber("Gyro Value (rate)", gyro.getRate());
        SmartDashboard.putNumber("Joystick Value Magnitude", joystick.getMagnitude());
        SmartDashboard.putNumber("Joystick Value Direction", joystick.getDirectionDegrees());
        SmartDashboard.putNumber("Joystick Direction - gyro rate", angle-(gyro.getRate()*Kp));
        SmartDashboard.putNumber("Joystick Value Twist", joystick.getTwist());
        */
	}
	
}

