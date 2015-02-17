package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.Forklift;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;

/**
 * 
 * Picks up one tote and then brings it into the autonomous zone
 * 
 * @author Ray
 *
 */
public class OneYellowToteAuto implements AutonomousProcedure
{
	private IDriverAssist iDriverAssist;
	private AutonomousProcedure driveIntoZone;
	private Forklift forkLift;
	private IDriveTrain mecanumDrive;
	private ITimeEventHandler clock;
	
	public OneYellowToteAuto(IDriverAssist iDriverAssist, AutonomousProcedure driveIntoZone, Forklift forkLift, IDriveTrain mecanumDrive, ITimeEventHandler clock) 
	{
		super();
		this.iDriverAssist = iDriverAssist;
		this.driveIntoZone = driveIntoZone;
		this.forkLift = forkLift;
		this.mecanumDrive = mecanumDrive;
		this.clock = clock;
	}

	@Override
	public void doAuto() 
	{
		driveIntoZone = new DriveBackwardIntoAuto(mecanumDrive, clock);
		
		forkLift.goToGround(); // Moves to bottom level of lifting positions to pick up the tote
		
		iDriverAssist.toteSweetSpot(); // Moves toward and squares with the tote that is placed in front of the robot at the beginning of a match
		iDriverAssist.correctRotation(); // Correct rotation in front of the tote
		
		forkLift.nextToteLength(); // Pick up tote
		
		// Move backward into the autonomous zone
		driveIntoZone.doAuto();
		
		// Drop totes
		forkLift.goToGround();
	}
}
