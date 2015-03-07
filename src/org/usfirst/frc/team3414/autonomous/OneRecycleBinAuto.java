package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;
import org.usfirst.frc.team3414.sensors.SensorConfig;
import org.usfirst.frc.team3414.sensors.SweetSpotMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	private ITimeEventHandler clock;
	private final long RUNTIME = 2000;

	public OneRecycleBinAuto()
	{
		super();
		// SmartDashboard.putBoolean("Doing Bin 1", false);
		driverAssist = AutonomousConfig.getInstance().getDriveAssist();
		forkLift = ActuatorConfig.getInstance().getForklift();
		this.clock = SensorConfig.getInstance().getClock();
		driveIntoZone = new DriveIntoAuto();
	}

	@Override
	public void doAuto()
	{
		// SmartDashboard.putBoolean("Doing Bin 1", true);
		// Moves to bottom level of lifting positions

		// driverAssist.binSweetSpot(SweetSpotMode.TOTE_WIDE); // Moves toward
		// recycle bin that is placed in front of the robot at the beginning of
		// a match
		// driverAssist.correctRotation(SweetSpotMode.TOTE_WIDE); // Correct
		// rotation in front of the recycle bin
		
		long idA = clock.addTimeListener((event) -> {
			forkLift.setEStopAllAction(true);

		}, RUNTIME + 2000, true);
		
		forkLift.previousToteLength();
		
		clock.removeListener(idA);
		forkLift.setEStopAllAction(false);
		
		
		long idB = clock.addTimeListener((event) -> {
			forkLift.setEStopAllAction(true);

		}, RUNTIME, true);
		
		forkLift.nextToteLength();
		
		clock.removeListener(idB);
		forkLift.setEStopAllAction(false);
		
		// forkLift.downLift(); // Pick up recycle bin
		// Timer.delay(0.75);
		// forkLift.stopLift();
		// Timer.delay(0.1);
		// forkLift.upLift();
		// Timer.delay(0.75);
		// Move backward into the autonomous zone
		driveIntoZone.doAuto();
		// forkLift.calibrate();
		// Drop recycle bin
		forkLift.goToBottomLimit();
		// forkLift.previousBinLength();

	}

}
