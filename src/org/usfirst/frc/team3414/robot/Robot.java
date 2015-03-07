package org.usfirst.frc.team3414.robot;

import org.usfirst.frc.team3414.autonomous.AutonomousControl;
import org.usfirst.frc.team3414.teleop.TeleopControl;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.vision.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	TeleopControl teleop;
	AutonomousControl auto;
	CameraServer server;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		RobotStatus.setIsRunning(true);
		server = CameraServer.getInstance();
		server.setQuality(25);
		//server.startAutomaticCapture("cam1");
		server.startAutomaticCapture("cam3");
		teleop = new TeleopControl();
		auto = new AutonomousControl();
	}

	public void autonomousInit()
	{
		//server.startAutomaticCapture("cam1");
		auto.doAuto();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{

	}

	public void teleopInit()
	{
		//server.startAutomaticCapture("cam3");
		teleop.enable();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{

	}

	public void testInit()
	{

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{

	}

	public void disabledInit()
	{
		teleop.disable();
	}

}
