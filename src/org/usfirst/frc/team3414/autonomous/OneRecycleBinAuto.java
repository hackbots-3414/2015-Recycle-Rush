package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.Forklift;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;

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
	
	private AutonomousProcedure driveIntoZone;
	
	public OneRecycleBinAuto(IDriverAssist iDriverAssist, AutonomousProcedure driveIntoZone, Forklift forkLift, IDriveTrain mecanumDrive, ITimeEventHandler clock) 
	{
		super();
		this.iDriverAssist = iDriverAssist;
		this.driveIntoZone = driveIntoZone;
		this.forkLift = forkLift;
		this.mecanumDrive = mecanumDrive;
		this.clock = clock;
	}

	private Forklift forkLift;
	
	private IDriveTrain mecanumDrive;
	
	private ITimeEventHandler clock;

	@Override
	public void doAuto() 
	{
		driveIntoZone = new DriveBackwardIntoAuto(mecanumDrive, clock);
		
		forkLift.goToGround(); // Moves to bottom level of lifting positions
		
		iDriverAssist.binSweetSpot(); // Moves toward recycle bin that is placed in front of the robot at the beginning of a match
		iDriverAssist.correctRotation(); // Correct rotation in front of the recycle bin
		
		forkLift.nextBinLength(); // Pick up recycle bin
		
		// Move backward into the autonomous zone
		driveIntoZone.doAuto();
		
		// Drop recycle bin
		forkLift.goToGround();
		
	}

}
