package org.usfirst.frc.team3414.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3414.sensors.*;
import org.usfirst.frc.team3414.autonomous.*;
import org.usfirst.frc.team3414.teleop.*;
import org.usfirst.frc.team3414.actuators.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	SensorConfig sensorConfig;
	ActuatorConfig actuatorConfig;
	TeleopControl teleop;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		// This must always get run at the start of init. Do not perform any
		// init before this is called
		RobotStatus.setIsRunning(true);
		sensorConfig = SensorConfig.getInstance();
		actuatorConfig = ActuatorConfig.getInstance();
		teleop = new TeleopControl(sensorConfig, actuatorConfig);
	}

	public void autonomousInit()
	{

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{

	}

	public void teleopInit()
	{

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		teleop.runTeleop();

		if (isSimulation())
		{
			Display.getInstance().putDiagnosticsData();
			teleop.isTestingRobot(true);
		} else if (isReal())
		{
			Display.getInstance().putGameData();
			teleop.isTestingRobot(false);
		}
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

}
