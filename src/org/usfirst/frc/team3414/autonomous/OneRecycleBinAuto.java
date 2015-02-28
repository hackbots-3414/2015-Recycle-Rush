package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.sensors.SweetSpotMode;

/**
 * 
 * Picks up one recycle bin and drops it in the autonomous zone
 * 
 * @author Ray
 *
 */
public class OneRecycleBinAuto implements AutonomousProcedure
{
	private IDriverAssist driverAssist;
	private AutonomousProcedure driveIntoZone;
	private ILiftAssist forkLift;
	
	public OneRecycleBinAuto() 
	{
		super();

		driverAssist = AutonomousConfig.getInstance().getDriveAssist();
		forkLift = ActuatorConfig.getInstance().getForklift();
		
		driveIntoZone = new DriveBackwardIntoAuto();
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
		
	}

}
