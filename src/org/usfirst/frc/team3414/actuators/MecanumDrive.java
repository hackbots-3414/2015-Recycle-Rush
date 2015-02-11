package org.usfirst.frc.team3414.actuators;

import edu.wpi.first.wpilibj.CANTalon;

import org.usfirst.frc.team3414.sensors.Accelerometer;
import org.usfirst.frc.team3414.sensors.Gyroscope;
import org.usfirst.frc.team3414.sensors.ITimeListener;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class MecanumDrive implements IDriveTrain, ITimeListener
{
    RobotDrive drive;
    Gyroscope gyro;
    Accelerometer accel;
    final double Kp = .03;
    double devAngle;
    SpeedController[] talons = new SpeedController[4];

    private static MecanumDrive singleton = null;

    private MecanumDrive()
    {

	gyro = new Gyroscope(1); // COME BACK AND CHANGE CHANNEL NUMBER TO MAKE
				 // THIS WORK!!!!!!!!!!!!!!!!!!!!!!!!!
	accel = new Accelerometer();
	devAngle = (Math.toDegrees(Math.atan(accel.getAccelY() / accel.getAccelX())));
	for (int i = 0; i < talons.length; i++)
	{
	    talons[i] = new CANTalon(i + 1, 10);
	}

	drive = new RobotDrive(talons[0], talons[1], talons[2], talons[3]);
	gyro.reset();
    }

    public static MecanumDrive createInstance()
    {
	if (singleton == null)
	{
	    singleton = new MecanumDrive();
	}

	return singleton;
    }

    public static MecanumDrive getInstance()
    {
	if (singleton == null)
	{
	    throw new NullPointerException(
		    "MecanumDrive hasn't been created yet");
	}

	return singleton;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */

    public void move(double velocity, double angle, double rotation)
    {
	drive.mecanumDrive_Polar(velocity, angle, rotation);
	while (devAngle > 5)
	{
	    drive.mecanumDrive_Polar(velocity, angle - devAngle, rotation);
	}
	while (devAngle < -5)
	{
	    drive.mecanumDrive_Polar(velocity, angle + devAngle, rotation);
	}
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    public void rotateSomeDegrees(double degrees)
    {
	double currentAngle = gyro.getDegrees();
	while(gyro.getDegrees() < currentAngle + degrees)
	{
	    drive.mecanumDrive_Polar(0, 0, .75);
	}
	drive.stopMotor();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    public void moveConstantVelocity(double speed, double direction)
    {
	this.move(speed, direction, 0); 
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    public void stop()
    {
	drive.mecanumDrive_Polar(0.0, 0, 0);
    }

    public void toLog()
    {
    }

    public void rotateToDegrees(double degrees)
    {
	while(Math.abs(gyro.getDegrees()-degrees) > 0)
	{
	    drive.mecanumDrive_Polar(0, 0, .75);
	}
	drive.stopMotor();
    }

    public void doWhenTimeElapsed()
    {
	
    }
}
