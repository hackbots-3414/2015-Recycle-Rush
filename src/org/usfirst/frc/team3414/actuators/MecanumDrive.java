package org.usfirst.frc.team3414.actuators;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.usfirst.frc.team3414.sensors.IClock;
import org.usfirst.frc.team3414.sensors.IMeasureAcceleration;
import org.usfirst.frc.team3414.sensors.IMeasureDirection;
import org.usfirst.frc.team3414.sensors.ITimeListener;
import org.usfirst.frc.team3414.sensors.TimeEventArgs;
import org.usfirst.frc.team3414.teleop.Display;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class MecanumDrive implements IDriveTrain, ITimeListener
{
	private double currentVelocity, currentAngle, currentRotation;
	private RobotDrive drive;
	private IMeasureDirection gyro;
	private IMeasureAcceleration accel;
	private double devAngle;
	private IClock clock;
	private static final double ROTATE_CONSTANT = 0.5;
	private double ROTATE_SECONDS_PER_DEGREE;
	private double ROTATE_POWER_INTO_MOTORS;
	private ExecutorService threadpool;

	long eventID;

	double Kp = 1.0;

	protected MecanumDrive(IClock handler, IMeasureAcceleration accelerometer, IMeasureDirection gyro, SpeedController leftFront,
			SpeedController rightFront, SpeedController leftRear, SpeedController rightRear)
	{
		threadpool = Executors.newFixedThreadPool(1);
		this.clock = handler;
		eventID = clock.addTimeListener(this, 2000, true);
		this.gyro = gyro;
		// THIS WORK!!!!!!!!!!!!!!!!!!!!!!!!!
		accel = accelerometer;
		drive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);
		// gyro.reset();
	}

	private boolean isGyroAvailable()
	{
		return gyro != null && gyro.getDegrees() != Double.NaN;
	}

	private void rotateDegreesGyroBased(double degrees, boolean clockWise)
	{
		double currentAngle = gyro.getDegrees();
		threadpool.submit(() -> {
			while (gyro.getDegrees() < currentAngle + degrees)
			{
				drive.mecanumDrive_Polar(0, 0, ROTATE_POWER_INTO_MOTORS);
			}
			MecanumDrive.this.stop();
		});
	}

	private void rotateDegreesTimeBased(double degrees, boolean clockWise)
	{
		long timeToRotate = (long) (degrees * ROTATE_SECONDS_PER_DEGREE);
		if (clockWise)
			this.move(0.0, 0.0, ROTATE_POWER_INTO_MOTORS);
		else
			this.move(0.0, 0.0, -ROTATE_POWER_INTO_MOTORS);
		clock.addTimeListener((eventInfo) -> {
			MecanumDrive.this.stop();
		}, timeToRotate);
	}

	public void move(double magnitude, double angle, double rotation)
	{
		this.currentVelocity = magnitude;
		this.currentAngle = angle;
		this.currentRotation = rotation;

		drive.mecanumDrive_Polar(magnitude, angle, rotation);

	}

	public void rotate(double degrees, boolean clockWise)
	{
		if (isGyroAvailable())
		{
			rotateDegreesGyroBased(degrees, clockWise);
		} else
		{
			rotateDegreesTimeBased(degrees, clockWise);
		}
	}

	/**
	 * Relative to Face Forward
	 * 
	 * @param speed
	 * @param direction
	 */
	public void moveConstantVelocity(double speed, double direction)
	{
		this.move(speed, direction, 0);
	}

	public void rotateConstantVelocity(boolean clockWise)
	{
		if (clockWise)
		{
			this.move(0.0, 0.0, ROTATE_CONSTANT);
		} else
		{
			this.move(0.0, 0.0, -ROTATE_CONSTANT);
		}

	}

	public void stop()
	{
		drive.mecanumDrive_Polar(0.0, 0, 0);
	}

	public void toDisplay()
	{
		Display.getInstance().setDriveData(accelX, accelY, gyro.getDegrees(), gyro.getChangeInDegreesPerSecond());
	}

	int UPPER_BOUND_ACCEL = 5;
	int LOWER_BOUND_ANGLE = 2;

	double accelY;
	double accelX;

	@Override
	public void timeEvent(TimeEventArgs timeEvent)
	{
		if (timeEvent.getTimeEventID() == eventID)
		{
			if (gyro != null)
			{
				drive.mecanumDrive_Polar(currentVelocity, currentAngle - gyro.getChangeInDegreesPerSecond() * Kp, currentRotation);
			}

			if (accel != null)
			{
				accelY = accel.getAccelY();
				accelX = accel.getAccelZ();

				if ((accelY < UPPER_BOUND_ACCEL && accelY > -UPPER_BOUND_ACCEL) && (accelX < UPPER_BOUND_ACCEL && accelX > -UPPER_BOUND_ACCEL))
				{
					devAngle = (Math.toDegrees(Math.atan(accelY / accelX)));
					if (devAngle > LOWER_BOUND_ANGLE)
					{
						drive.mecanumDrive_Polar(currentVelocity, currentAngle - devAngle, currentRotation);
					}
					if (devAngle < -LOWER_BOUND_ANGLE)
					{
						drive.mecanumDrive_Polar(currentVelocity, currentAngle - devAngle, currentRotation);
					}
				}
			}
		}

	}
}
