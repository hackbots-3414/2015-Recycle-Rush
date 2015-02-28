package org.usfirst.frc.team3414.teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Display
{

	private static Display singleton = null;

	// FORKLIFT
	private double currentForkPosition;
	private double encoderRate;
	private boolean topLimitHit;
	private boolean bottomLimitHit;
	// JOYSTICK
	private double magnitude;
	private double direction;
	private double twist;
	// MECANUM_DRIVE
	private double accelX;
	private double accelY;
	private double gyroDegrees;
	private double gyroRate;

	private Display()
	{
	}

	public static Display getInstance()
	{
		if (singleton == null)
		{
			singleton = new Display();
		}

		return singleton;
	}

	public void putGameData()
	{
		// FORKLIFT
		SmartDashboard.putNumber("Current Forklift Position: ", currentForkPosition);
		SmartDashboard.putBoolean("Top Limit Switch Hit: ", topLimitHit);
		SmartDashboard.putBoolean("Bottom Limit Switch Hit: ", bottomLimitHit);
	}

	public void putDiagnosticsData()
	{
		// FORKLIFT
		SmartDashboard.putNumber("Current Forklift Position: ", currentForkPosition);
		SmartDashboard.putNumber("Forklift Encoder Rate: ", encoderRate);
		SmartDashboard.putBoolean("Top Limit Switch Hit: ", topLimitHit);
		SmartDashboard.putBoolean("Bottom Limit Switch Hit: ", bottomLimitHit);
		// JOYSTICK
		SmartDashboard.putNumber("Joystick Magnitude: ", magnitude);
		SmartDashboard.putNumber("Joystick Direction: ", direction);
		SmartDashboard.putNumber("Joystick Twist: ", twist);
		// DRIVE_TRAIN
		SmartDashboard.putNumber("Accelerometer X: ", accelX);
		SmartDashboard.putNumber("Accelerometer Y: ", accelY);
		SmartDashboard.putNumber("Gyro Degrees: ", gyroDegrees);
		SmartDashboard.putNumber("Gyro Rate (Degrees Per Second): ", gyroRate);
	}

	public void setDriveData(double _accelX, double _accelY, double _gyroDegrees, double _gyroRate)
	{
		this.accelX = _accelX;
		this.accelY = _accelY;
		this.gyroDegrees = _gyroDegrees;
		this.gyroRate = _gyroRate;
	}

	public void setForkliftData(double currentPosition, double rate, boolean _topLimitHit, boolean _bottomLimitHit)
	{
		this.currentForkPosition = currentPosition;
		this.encoderRate = rate;
		this.topLimitHit = _topLimitHit;
		this.bottomLimitHit = _bottomLimitHit;
	}

	public void setJoyData(double _magnitude, double _direction, double _twist)
	{
		this.magnitude = _magnitude;
		this.direction = _direction;
		this.twist = _twist;
	}

}
