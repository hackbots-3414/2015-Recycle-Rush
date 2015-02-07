package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.teleop.Display;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class MecanumDrive implements IDriveTrain
{
	RobotDrive drive;
	Gyro gyro;
	final double Kp = 1;
	double angle;
	SpeedController[] talons = new SpeedController[4];

	private static MecanumDrive singleton = null;

	private MecanumDrive()
	{

		// this.gyro = new Gyro();

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
			throw new NullPointerException("MecanumDrive hasn't been created yet");
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
		/*
		 * drive.mecanumDrive_Polar(velocity * .2,(angle - (gyro.getRate() * Kp) * .2), rotation * .2);
		 * drive.mecanumDrive_Polar(velocity, angle-(gyro.getRate()*Kp),rotation -(gyro.getAngle()-1.0)/180.0 );
		 */
		
		gyro.reset();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	public void rotateSomeDegrees(double degrees)
	{
		drive.mecanumDrive_Polar(0, 0, degrees);
		// TODO: Rely on Gyro output- turn X Degrees
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	public void moveConstantVelocity(double speed, double direction)
	{
		drive.mecanumDrive_Polar(speed, direction, 0); // TODO: incorporate
														// Gyro-correct for
														// drive
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
		// Display.getInstance().setDriveData(gyro.getRate(),
		// joystick.getMagnitude(), joystick.getDirectionDegrees(),
		// angle - (gyro.getRate() * Kp), joystick.getTwist());
	}

	public void rotateToDegrees(double degrees)
	{
		/*
		 * TODO If there's a compass: public void rotateToPosition(int position) {
		 * //compare compass to gyro }
		 */

	}
}
