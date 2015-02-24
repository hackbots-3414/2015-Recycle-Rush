package org.usfirst.frc.team3414.robot;

import org.usfirst.frc.team3414.sensors.SensorConfig;
import org.usfirst.frc.team3414.teleop.TeleopControl;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
//	SensorConfig sensorConfig;
//	ActuatorConfig actuatorConfig;
//	private boolean a = false;
//	private boolean b = false;
//	IJoystick joy;
	
	TeleopControl teleop;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{	
		//This must always get run at the start of init. Do not perform any init before this is called
		RobotStatus.setIsRunning(true);
		
//		sensorConfig = SensorConfig.getInstance();
//		actuatorConfig = ActuatorConfig.getInstance();
		
		teleop = new TeleopControl();
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
		teleop.enable();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		
//		while(joy.getButtonSeven())
//		{
//			((Forklift)actuatorConfig.getForklift()).up();
//		}
//		if(joy.getButtonEight())
//		{
//			while(!joy.getButtonEight())
//			{
//				((Forklift)actuatorConfig.getForklift()).down();
//			}
//			
//			// Currently the forklift does not engage when it goes down, only when going up.
//			// This is so because if we call this function it does not 'end' so to speak. It goes through the loop forever...
//			// Use the tote up and down functions
//		}
//		
//		((Forklift)actuatorConfig.getForklift()).stopLift();
//		
//		if(joy.getButtonNine())
//		{
//			actuatorConfig.getDriveTrain().move(0, 0, SmartDashboard.getNumber("Rotational Speed", 0.5));
//		} if(joy.getButtonTwelve())
//		{
//			actuatorConfig.getServo().engage();
//		} if(joy.getButtonEleven())
//		{
//			actuatorConfig.getServo().disengage();
//		}
//		else{
//			actuatorConfig.getDriveTrain().move(joy.getMagnitude(), joy.getDirectionDegrees(), joy.getTwist());
//		}
		
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
