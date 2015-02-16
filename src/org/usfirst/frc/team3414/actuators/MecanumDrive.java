package org.usfirst.frc.team3414.actuators;

import edu.wpi.first.wpilibj.CANTalon;

import org.usfirst.frc.team3414.sensors.Gyroscope;
import org.usfirst.frc.team3414.sensors.IMeasureAcceleration;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;
import org.usfirst.frc.team3414.sensors.ITimeListener;
import org.usfirst.frc.team3414.sensors.TimeEventArgs;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class MecanumDrive implements IDriveTrain, ITimeListener {
	double currentVelocity, currentAngle, currentRotation;
	RobotDrive drive;
	Gyroscope gyro;
	IMeasureAcceleration accel;
	final double Kp = .03;
	double devAngle;
	SpeedController[] talons = new SpeedController[4];
	private ITimeEventHandler clock;

	protected MecanumDrive(ITimeEventHandler handler, IMeasureAcceleration accelerometer) {
		this.clock = handler;
		clock.addListener(this, 2000, true);
		gyro = new Gyroscope(1); // COME BACK AND CHANGE CHANNEL NUMBER TO MAKE
		// THIS WORK!!!!!!!!!!!!!!!!!!!!!!!!!
		accel = accelerometer;

		for (int i = 0; i < talons.length; i++) {
			talons[i] = new CANTalon(i + 1, 10);
		}

		drive = new RobotDrive(talons[0], talons[1], talons[2], talons[3]);
		gyro.reset();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	public void move(double velocity, double angle, double rotation) {
		this.currentVelocity = velocity;
		this.currentAngle = angle;
		this.currentRotation = rotation;

		drive.mecanumDrive_Polar(velocity, angle, rotation);

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	public void rotateSomeDegrees(double degrees) {
		double currentAngle = gyro.getDegrees();
		while (gyro.getDegrees() < currentAngle + degrees) {
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
	public void moveConstantVelocity(double speed, double direction) {
		this.move(speed, direction, 0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	public void stop() {
		drive.mecanumDrive_Polar(0.0, 0, 0);
	}

	public void toLog() {
	}

	public void rotateToDegrees(double degrees) {
		while (Math.abs(gyro.getDegrees() - degrees) > 0) {
			drive.mecanumDrive_Polar(0, 0, .75);
		}
		drive.stopMotor();
	}

	@Override
	public void timeEvent(TimeEventArgs timeEvent) {
		devAngle = (Math.toDegrees(Math.atan(accel.getAccelY()
				/ accel.getAccelZ())));
		if (devAngle > 5) {
			drive.mecanumDrive_Polar(currentVelocity, currentAngle - devAngle,
					currentRotation);
		}
		if (devAngle < -5) {
			drive.mecanumDrive_Polar(currentVelocity, currentAngle - devAngle,
					currentRotation);
		}

	}
}
