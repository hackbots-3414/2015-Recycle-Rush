package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.sensors.SweetSpotMode;

/**
 * 
 * Picks up one tote and then brings it into the autonomous zone
 * 
 * @author Ray
 *
 */
public class OneYellowToteAuto implements AutonomousProcedure
{
	private IDriverAssist driverAssist;
	private AutonomousProcedure driveIntoZone;
	private ILiftAssist forkLift;
	
	public OneYellowToteAuto() 
	{
		super();
		
		ActuatorConfig actuators = ActuatorConfig.getInstance();
		AutonomousConfig autonomous = AutonomousConfig.getInstance();
		
		this.driverAssist = autonomous.getDriveAssist();
		driveIntoZone = new DriveIntoAuto();
		this.forkLift = actuators.getForklift();
		
		driveIntoZone = new DriveBackwardIntoAuto();
	}

	@Override
	public void doAuto() 
	{
		forkLift.goToBottomLimit(); // Moves to bottom level of lifting positions to pick up the tote
		
		driverAssist.toteSweetSpot(SweetSpotMode.TOTE_WIDE); // Moves toward and squares with the tote that is placed in front of the robot at the beginning of a match
		driverAssist.correctRotation(SweetSpotMode.TOTE_WIDE); // Correct rotation in front of the tote
		
		forkLift.nextToteLength(); // Pick up tote
		
		// Move backward into the autonomous zone
		driveIntoZone.doAuto();
		
		// Drop totes
		forkLift.goToBottomLimit();
	}
}
