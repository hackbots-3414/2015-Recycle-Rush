package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.MecanumDrive;
import org.usfirst.frc.team3414.sensors.Camera;
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
public class DriveBackwardIntoAuto implements AutonomousProcedure
{	
	private IDriveTrain mecanumDrive;
	private IVision camera;
	
	public DriveBackwardIntoAuto() 
	{
		super();
		
		this.mecanumDrive = ActuatorConfig.getInstance().getDriveTrain();
		this.camera = SensorConfig.getInstance().getVisionAssist();
	}

	public void doAuto()
	{
		mecanumDrive.move(0, -1.0, 0.0); // Move backward into the autonomous zone
		
		while(cameraAssist.isInAutoZone() == false)
		{
			
		}
		
		mecanumDrive.stop(); // Stops when we get into the autonomous zone
	}
}
