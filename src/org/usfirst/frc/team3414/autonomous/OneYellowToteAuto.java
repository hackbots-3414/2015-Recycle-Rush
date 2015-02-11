package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ForkLift;
import org.usfirst.frc.team3414.actuators.ILiftAssist;

/**
 * 
 * Picks up one tote and then brings it into the autonomous zone
 * 
 * @author Ray
 *
 */
public class OneYellowToteAuto implements AutonomousProcedure
{
	public IDriverAssist iDriverAssist;
	
	AutonomousProcedure driveIntoZone;
	
	ILiftAssist forkLift = ForkLift.getInstance();

	@Override
	public void doAuto() 
	{
		driveIntoZone = new DriveBackwardIntoAuto();
		
		forkLift.goToBottom(); // Moves to bottom level of lifting positions to pick up the tote
		
		iDriverAssist.toteSweetSpot(); // Moves toward and squares with the tote that is placed in front of the robot at the beginning of a match
		iDriverAssist.correctRotation(); // Correct rotation in front of the tote
		
		forkLift.nextToteLength(); // Pick up tote
		
		// Move backward into the autonomous zone
		driveIntoZone.doAuto();
		
		// Drop totes
		forkLift.goToBottom();
		
	}

}
