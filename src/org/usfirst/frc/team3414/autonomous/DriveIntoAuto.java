package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;
import org.usfirst.frc.team3414.sensors.ITimeListener;
import org.usfirst.frc.team3414.sensors.SensorConfig;
import org.usfirst.frc.team3414.sensors.TimeEventArgs;

import edu.wpi.first.wpilibj.Timer;

/**
 * An autonomous routine that drives into the autonomous zone and does nothing
 * else
 * 
 * @author Ray
 *
 */
public class DriveIntoAuto implements AutonomousProcedure, ITimeListener
{
	private IVision cameraAssist;
	private IDriveTrain mecanumDrive;
	private ITimeEventHandler clock;
	private final long RUNTIME = 2100; //previously 2250

	public DriveIntoAuto()
	{
		super();

		SensorConfig sensors = SensorConfig.getInstance();

		this.mecanumDrive = ActuatorConfig.getInstance().getDriveTrain();
		this.clock = sensors.getClock();
		this.cameraAssist = sensors.getVisionAssist();
	}

	public void doAuto()
	{

		//mecanumDrive.move(-0.5, 0, 0.0); // Move backward into the autonomous
			
		long current = System.currentTimeMillis();
		while (System.currentTimeMillis() < current + RUNTIME) {
			mecanumDrive.move(-0.5, 0, 0.0);
		}
		mecanumDrive.stop();
		
//		long current = System.currentTimeMillis();
//		while (System.currentTimeMillis() < current + RUNTIME) {
//			mecanumDrive.move(-0.5, 0, 0.0);
//		}
//		mecanumDrive.stop();
		//clock.addTimeListener(this, 3000, false);// zone
//		clock.addTimeListener((event) -> {
//			mecanumDrive.stop();
//		}, 3000, false); 
		
		// Timer.delay(2); //Change for timeout value (this
							// sets the value where the robot gives up on
							// finding the line and just stops by timebase)
		//
		// while(cameraAssist.isInAutoZone() == false)
		// {
		//
		// }

		// mecanumDrive.stop(); // Stops when we get into the autonomous zone
	}

	@Override
	public void timeEvent(TimeEventArgs timeEvent)
	{
		mecanumDrive.stop();
	}

}
