package org.usfirst.frc.team3414.autonomous;

import java.util.List;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.sensors.SensorConfig;
import org.usfirst.frc.team3414.sensors.SweetSpotMode;
import org.usfirst.frc.team3414.autonomous.Obstacle;

/**
 * 
 * Picks up two recycle bins and drops the stack in the autonomous zone
 * 
 * @author Ray
 *
 */
public class TwoRecycleBinAuto implements AutonomousProcedure
{
	private IDriverAssist driverAssist;

	private AutonomousProcedure driveIntoZone;
	
	private IVision cameraAssist;
	
	private ILiftAssist forkLift;
	
	private IDriveTrain mecanumDrive;

	private boolean detected = false;


	public TwoRecycleBinAuto()
	{
		super();
		
		ActuatorConfig actuators = ActuatorConfig.getInstance();
		
		this.driverAssist = AutonomousConfig.getInstance().getDriveAssist();
		this.forkLift = actuators.getForklift();
		this.mecanumDrive = actuators.getDriveTrain();
		this.cameraAssist = SensorConfig.getInstance().getVisionAssist();
		
		driveIntoZone = new DriveIntoAuto();
	}
	
	@Override
	public void doAuto() 
	{
		forkLift.goToBottomLimit(); // Moves to bottom level of lifting positions
		
		driverAssist.binSweetSpot(SweetSpotMode.TOTE_WIDE); // Moves toward recycle bin that is placed in front of the robot at the beginning of a match
		driverAssist.correctRotation(SweetSpotMode.TOTE_WIDE); // Correct rotation in front of the recycle bin
		
		forkLift.nextBinLength(); // Pick up recycle bin
		
		// Move backward into the autonomous zone
		driveIntoZone.doAuto();
		
		// Drop recycle bin
		forkLift.goToBottomLimit();
		
		// Move sideways until the next bin is in sight
		mecanumDrive.move(90, 1.0, 0);
		
		while (!detected) {
		List<Obstacle> obstacleList = cameraAssist.getObjects();
		for(int i=0; i <= obstacleList.size(); i++)
		{
			if(obstacleList.get(i).type == ObjectType.TRASHCAN)
			{
				mecanumDrive.stop(); // If the next tote is in vision stop
			}
		}
		}
		
		// Approach the bin
		mecanumDrive.move(0, 1.0, 0);
		
		// TODO: slow to a stop when sensor bar detects the next bin
		
		driverAssist.binSweetSpot(SweetSpotMode.TOTE_WIDE); // get square with the bin after we are driven close enough
		
		forkLift.nextBinLength(); // Pick up the bin
		
		driveIntoZone.doAuto(); // Drive backward into the autonomous zone
		
		forkLift.goToBottomLimit(); // Drop the bin in the autonomous zone
	}

}
