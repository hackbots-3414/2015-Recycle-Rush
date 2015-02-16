package org.usfirst.frc.team3414.autonomous;

import java.util.List;

import org.usfirst.frc.team3414.actuators.Forklift;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.MecanumDrive;
import org.usfirst.frc.team3414.sensors.Camera;
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
	public IDriverAssist iDriverAssist;
	
	AutonomousProcedure driveIntoZone;
	//VirtualClock clock = new VirtualClock(null); // TODO: CHANGE WHEN SINGLETON
	Camera cameraAssist = new Camera();
	
	Forklift forkLift = Forklift.getInstance();
	IDriveTrain mecanumDrive = MecanumDrive.getInstance();
	
	boolean detected = false;
	
	@Override
	public void doAuto() 
	{
		driveIntoZone = new DriveBackwardIntoAuto();
		
		forkLift.goToGround(); // Moves to bottom level of lifting positions
		
		iDriverAssist.binSweetSpot(); // Moves toward recycle bin that is placed in front of the robot at the beginning of a match
		iDriverAssist.correctRotation(); // Correct rotation in front of the recycle bin
		
		forkLift.nextBinLength(); // Pick up recycle bin
		
		// Move backward into the autonomous zone
		driveIntoZone.doAuto();
		
		// Drop recycle bin
		forkLift.goToGround();
		
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
		
		iDriverAssist.binSweetSpot(); // get square with the bin after we are driven close enough
		
		forkLift.nextBinLength(); // Pick up the bin
		
		driveIntoZone.doAuto(); // Drive backward into the autonomous zone
		
		forkLift.goToGround(); // Drop the bin in the autonomous zone
	}

}
