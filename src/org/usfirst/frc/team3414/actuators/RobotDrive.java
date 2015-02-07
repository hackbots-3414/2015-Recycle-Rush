package org.usfirst.frc.team3414.actuators;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc.team3414.teleop.*;

public class RobotDrive
{

	//TODO: Make this class to receive Teleop/ Autonomous Functions
	//MecanumDrive class should consist of the technical level methods: either initializing 
	//		or "has a" (encompasses in a constructor the) hardware
	//TODO: Adjust IDriveTrain
	
	SpeedController[] talons = new SpeedController[4];
	Gyro gyro;
	final double Kp = 1;
	double angle;

	MecanumDrive robot;
	Joystick joystick;

	public RobotDrive(Gyro _gyro, Joystick _joystick)
	{
		this.gyro = _gyro;
		this.joystick = _joystick;

		for (int i = 0; i < talons.length; i++)
		{
			talons[i] = new CANTalon(i + 1, 10);
		}
		robot = new MecanumDrive(talons[0], talons[1], talons[2], talons[3]);

		gyro.reset();
	}

	public void teleopPeriodic()
	{

		angle = joystick.getDirectionDegrees();
		// robot.move(joystick.getMagnitude(), angle-(gyro.getRate()*Kp),
		// joystick.getTwist()-(gyro.getAngle()-1.0)/180.0 );
		robot.move(joystick.getMagnitude() * .2,
				(angle - (gyro.getRate() * Kp) * .2), joystick.getTwist() * .2);
		// robot.move(joystick.getMagnitude(), joystick.getDirectionDegrees(),
		// joystick.getTwist());
		// robot.move(0.5, 0, 0);
		gyro.reset();

	}

	public void toLog()
	{
		Display.getInstance().setDriveData(gyro.getRate(),
				joystick.getMagnitude(), joystick.getDirectionDegrees(),
				angle - (gyro.getRate() * Kp), joystick.getTwist());
	}
}
