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
	// TELEOP BUTTONS
	private boolean driverOverride;
	private boolean suckerLeft;
	private boolean suckerRight;
	private boolean suckerIn;
	private boolean suckerOut;
	private boolean forkUp;
	private boolean forkDown;

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
		SmartDashboard.putBoolean("Driver Override: ", driverOverride);
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
		// TELEOP_BUTTONS
		SmartDashboard.putBoolean("Driver Override: ", driverOverride);
		SmartDashboard.putBoolean("Sucker Left: ", suckerLeft);
		SmartDashboard.putBoolean("Sucker Right: ", suckerRight);
		SmartDashboard.putBoolean("Sucker In: ", suckerIn);
		SmartDashboard.putBoolean("Sucker Out: ", suckerOut);
		SmartDashboard.putBoolean("Fork Up: ", forkUp);
		SmartDashboard.putBoolean("Fork Down: ", forkDown);
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

	public void setButtonData(boolean _driverOverride, boolean _suckerLeft, boolean _suckerRight, boolean _suckerIn, boolean _suckerOut, boolean _forkUp, boolean _forkDown)
	{
		this.driverOverride = _driverOverride;
		this.suckerLeft = _suckerLeft;
		this.suckerRight = _suckerRight;
		this.suckerIn = _suckerIn;
		this.suckerOut = _suckerOut;
		this.forkUp = _forkUp;
		this.forkDown = _forkDown;
	}

}
