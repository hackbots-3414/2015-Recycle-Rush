package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ForkLift;
import org.usfirst.frc.team3414.actuators.ILiftAssist;

/**
 * 
 * Picks up one recycle bin and drops it in the autonomous zone
 * 
 * @author Ray
 *
 */
public class OneRecycleBinAuto implements AutonomousProcedure
{
	public IDriverAssist iDriverAssist;
	
	AutonomousProcedure driveIntoZone;
	
	ILiftAssist forkLift = ForkLift.getInstance();

	@Override
	public void doAuto() 
	{
		driveIntoZone = new DriveBackwardIntoAuto();
		
		forkLift.goToBottom(); // Moves to bottom level of lifting positions
		
		iDriverAssist.binSweetSpot(); // Moves toward recycle bin that is placed in front of the robot at the beginning of a match
		iDriverAssist.correctRotation(); // Correct rotation in front of the recycle bin
		
		forkLift.nextBinLength(); // Pick up recycle bin
		
		// Move backward into the autonomous zone
		driveIntoZone.doAuto();
		
		// Drop recycle bin
		forkLift.goToBottom();
		
	}

}
