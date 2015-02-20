package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.MecanumDrive;
import org.usfirst.frc.team3414.sensors.Camera;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;
import org.usfirst.frc.team3414.sensors.ITimeListener;
import org.usfirst.frc.team3414.sensors.TimeEventArgs;
/**
 * An autonomous routine that drives into the autonomous zone and does nothing else
 * 
 * @author Ray
 *
 */
public class DriveIntoAuto implements AutonomousProcedure, ITimeListener
{
	Camera cameraAssist = new Camera(); // Should be singleton
	
	IDriveTrain mecanumDrive;
	ITimeEventHandler clock;

	public DriveIntoAuto(IDriveTrain mecanumDrive, ITimeEventHandler clock) 
	{
		super();
		
		this.mecanumDrive = mecanumDrive;
		this.clock = clock;
	}
	
	public void doAuto()
	{
		mecanumDrive.move(0, 1.0, 0.0); // Move forward into the autonomous zone
		clock.addTimeListener(this, 3000); //Change for timeout value (this sets the value where the robot gives up on finding the line and just stops by timebase)
		
		while(cameraAssist.areWeInAutoZone() == false)
		{
			
		}
		
		mecanumDrive.stop(); // Stops when we get into the autonomous zone
	}

	@Override
	public void timeEvent(TimeEventArgs timeEvent) 
	{
		mecanumDrive.stop();
	}

}
