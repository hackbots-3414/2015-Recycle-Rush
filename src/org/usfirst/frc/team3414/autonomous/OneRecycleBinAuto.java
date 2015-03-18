package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;
import org.usfirst.frc.team3414.sensors.SensorConfig;
//import org.usfirst.frc.team3414.sensors.SweetSpotMode;

/**
 * 
 * Picks up one recycle bin and drops it in the autonomous zone
 * 
 * @author Ray
 *
 */
public class OneRecycleBinAuto implements AutonomousProcedure
{
//	private IDriverAssist driverAssist;
	private AutonomousProcedure driveIntoZone;
	private ILiftAssist forkLift;
	private ITimeEventHandler clock;
	private final long RUNTIME = 2000;

	public OneRecycleBinAuto()
	{
		super();
//		driverAssist = AutonomousConfig.getInstance().getDriveAssist();
		forkLift = ActuatorConfig.getInstance().getForklift();
		clock = SensorConfig.getInstance().getClock();
		driveIntoZone = new DriveIntoAuto();
	}

	@Override
	/**
	 * ForkLift moves down for either 4 seconds or until finished moving
	 * ForkLift moves up for either 2 seconds or until finished moving
	 * Move backward into the autonomous zone
	 * Drop recycle bin at bottom
	 */
	public void doAuto()
	{
		// ForkLift moves down for either 4 seconds or until finished moving
		long idA = clock.addTimeListener((event) -> {
			forkLift.setEStopAllAction(false);

		}, RUNTIME + 2000, false);
		
		forkLift.previousToteLength();
		
		clock.removeListener(idA);
		forkLift.setEStopAllAction(true);
		
		// ForkLift moves up for either 2 seconds or until finished moving
		long idB = clock.addTimeListener((event) -> {
			forkLift.setEStopAllAction(false);

		}, RUNTIME, false);
		
		forkLift.nextToteLength();
		
		clock.removeListener(idB);
		forkLift.setEStopAllAction(false);
		
		// Move backward into the autonomous zone
		driveIntoZone.doAuto();
		
		// Drop recycle bin
		forkLift.goToBottomLimit();

		
		
		// Moves to bottom level of lifting positions
				// driverAssist.binSweetSpot(SweetSpotMode.TOTE_WIDE); // Moves toward
				// recycle bin that is placed in front of the robot at the beginning of
				// a match
				// driverAssist.correctRotation(SweetSpotMode.TOTE_WIDE); // Correct
				// rotation in front of the recycle bin
	}

}
