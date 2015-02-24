package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;
import org.usfirst.frc.team3414.sensors.ITimeListener;
import org.usfirst.frc.team3414.sensors.SensorConfig;
import org.usfirst.frc.team3414.sensors.TimeEventArgs;

/**
 * An autonomous routine that drives backward into the autonomous zone and does nothing else, used in other autonomous methods.
 * 
 * @author Ray
 *
 */
public class DriveBackwardIntoAuto implements AutonomousProcedure, ITimeListener
{
	IVision cameraAssist;
	IDriveTrain mecanumDrive;
	ITimeEventHandler clock;

	public DriveBackwardIntoAuto() 
	{
		super();
		
		SensorConfig sensors = SensorConfig.getInstance();
		ActuatorConfig actuators = ActuatorConfig.getInstance();
		
		this.mecanumDrive = actuators.getDriveTrain();
		this.clock = sensors.getClock();
		this.cameraAssist = sensors.getVisionAssist();
	}

	public void doAuto()
	{
		clock.addTimeListener(this, 3000); //Change for timeout value (this sets the value where the robot gives up on finding the line and just stops by timebase)
		
		mecanumDrive.move(0, -1.0, 0.0); // Move backward into the autonomous zone
		
		while(cameraAssist.isInAutoZone() == false)
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
