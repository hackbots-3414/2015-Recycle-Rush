package org.usfirst.frc.team3414.autonomous;

import java.util.List;

import org.usfirst.frc.team3414.actuators.ForkLift;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.actuators.MecanumDrive;
import org.usfirst.frc.team3414.sensors.Camera;
import org.usfirst.frc.team3414.autonomous.Obstacle;

/**
 * 
 * Picks up two totes and drops the stack in the autonomous zone
 * 
 * @author Ray
 *
 */
public class TwoYellowToteAuto implements AutonomousProcedure {
	public IDriverAssist iDriverAssist;

	AutonomousProcedure driveIntoZone;
	//VirtualClock clock = new VirtualClock(null); // TODO: CHANGE WHEN SINGLETON
	Camera cameraAssist = new Camera();

	ILiftAssist forkLift = ForkLift.getInstance();
	IDriveTrain mecanumDrive = MecanumDrive.getInstance();

	List<Obstacle> obstacleList;

	boolean detected = false;

	@Override
	public void doAuto() {
		driveIntoZone = new DriveBackwardIntoAuto();

		forkLift.goToBottom(); // Moves to bottom level of lifting positions

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

		forkLift.goToBottom(); // Put tote back on the other tote and go back
								// down to the bottom position so we can lift
								// the whole stack up

		// Lift up the whole stack
		forkLift.nextBinLength();

		// Move backward into the autonomous zone
		driveIntoZone.doAuto();

		// Drop totes
		forkLift.goToBottom();

	}

}
