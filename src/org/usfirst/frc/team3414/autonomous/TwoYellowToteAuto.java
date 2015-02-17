package org.usfirst.frc.team3414.autonomous;

import java.util.List;

import org.usfirst.frc.team3414.actuators.Forklift;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.MecanumDrive;
import org.usfirst.frc.team3414.sensors.Camera;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;
import org.usfirst.frc.team3414.autonomous.Obstacle;

/**
 * 
 * Picks up two totes and drops the stack in the autonomous zone
 * 
 * @author Ray
 *
 */
public class TwoYellowToteAuto implements AutonomousProcedure 
{
	private IDriverAssist iDriverAssist;

	private AutonomousProcedure driveIntoZone;
	//VirtualClock clock = new VirtualClock(null); // TODO: CHANGE WHEN SINGLETON
	private Camera cameraAssist = new Camera();

	private Forklift forkLift;
	private IDriveTrain mecanumDrive;
	
	private ITimeEventHandler clock;

	private List<Obstacle> obstacleList;

	private boolean detected = false;
	
	public TwoYellowToteAuto(IDriverAssist iDriverAssist, AutonomousProcedure driveIntoZone, Camera cameraAssist, Forklift forkLift, IDriveTrain mecanumDrive, ITimeEventHandler clock) 
	{
		super();
		this.iDriverAssist = iDriverAssist;
		this.driveIntoZone = driveIntoZone;
		this.cameraAssist = cameraAssist;
		this.forkLift = forkLift;
		this.mecanumDrive = mecanumDrive;
		this.clock = clock;
	}
	
	@Override
	public void doAuto() 
	{
		driveIntoZone = new DriveBackwardIntoAuto(mecanumDrive, clock);

		forkLift.goToGround(); // Moves to bottom level of lifting positions

		iDriverAssist.toteSweetSpot(); // Moves toward tote that is placed in
										// front of the robot at the beginning
										// of a match
		iDriverAssist.correctRotation(); // Correct rotation in front of the
											// tote

		forkLift.nextToteLength(); // Pick up tote
		forkLift.nextToteLength(); // Go up one more so we can get the second
									// tote easily

		// Move backward slightly and then move in the X direction until we
		// reach the second yellow tote
		mecanumDrive.move(0, -1.0, 0);
		try {
			Thread.sleep(100); // TODO: Replace with a closed loop system
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mecanumDrive.stop();
		mecanumDrive.move(1.0, 90, 0); // Move at a 90 degree angle to the other
										// tote

		// Wait until the previous tote gets out of vision
		// TODO: replace with a closed loop system (detect when one tote leaves
		// and when the other enters)
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (!detected) {
			obstacleList = cameraAssist.getObjects();

			for (int i = 0; i <= obstacleList.size(); i++) {
				if (obstacleList.get(i).type == ObjectType.TOTE) {
					mecanumDrive.stop(); // If the next tote is in vision, stop
				}
			}
		}

		iDriverAssist.toteSweetSpot(); // get square with the tote and drive up
										// to it correctly

		forkLift.goToGround(); // Put tote back on the other tote and go back
								// down to the bottom position so we can lift
								// the whole stack up

		// Lift up the whole stack
		forkLift.nextBinLength();

		// Move backward into the autonomous zone
		driveIntoZone.doAuto();

		// Drop totes
		forkLift.goToGround();
	}
}
