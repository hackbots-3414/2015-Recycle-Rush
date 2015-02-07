package org.usfirst.frc.team3414.actuators;

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

	public MecanumDrive(SpeedController frontLeftChannel, SpeedController frontRightChannel, SpeedController backLeftChannel,
			SpeedController backRightChannel) // Possibly include Gyro and Compass and Accel in Constructor?
	{
		drive = new RobotDrive(frontLeftChannel, backLeftChannel, frontRightChannel, backRightChannel);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	public void move(double velocity, double angle, double rotation)
	{
		drive.mecanumDrive_Polar(velocity, angle, rotation); // TODO: fix
																// Gyro-correct
																// for drive
		/*
		 * robot.move(joystick.getMagnitude() * .2,
		 * (joystick.getDirectionDegrees() - (gyro.getRate() * Kp) * .2),
		 * joystick.getTwist() * .2);
		 */
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

	/*
	 * TODO If there's a compass: public void rotateToPosition(int position) {
	 * //compare compass to gyro }
	 */

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

	@Override
	public void rotateToDegrees(double degrees)
	{
		// TODO Auto-generated method stub

	}
}
