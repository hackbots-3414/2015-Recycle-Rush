package org.usfirst.frc.team3414.autonomous;

import java.util.List;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.logger.LogData;
import org.usfirst.frc.team3414.sensors.SensorConfig;
import org.usfirst.frc.team3414.sensors.SweetSpotMode;
import org.usfirst.frc.team3414.autonomous.Obstacle;

/**
 * 
 * Picks up three totes and drops the stack in the autonomous zone
 * 
 * @author Ray
 *
 */
public class ThreeYellowToteAuto implements AutonomousProcedure {
	
	private IDriverAssist driverAssist;

	private AutonomousProcedure driveIntoZone;
	
	private IVision cameraAssist;
	
	private ILiftAssist forkLift;
	
	private IDriveTrain mecanumDrive;
	
	private List<Obstacle> obstacleList;

	private boolean detected = false;


	public ThreeYellowToteAuto() 
	{
		super();
		
		ActuatorConfig actuators = ActuatorConfig.getInstance();
		
		this.driverAssist = AutonomousConfig.getInstance().getDriveAssist();
		this.forkLift = actuators.getForklift();
		this.mecanumDrive = actuators.getDriveTrain();
		this.cameraAssist = SensorConfig.getInstance().getVisionAssist();
		
		driveIntoZone = new DriveIntoAuto();
	}
	
	@Override
	public void doAuto() 
	{
		forkLift.goToGround(); // Moves to bottom level of lifting positions

		driverAssist.toteSweetSpot(SweetSpotMode.TOTE_WIDE); // Moves toward tote that is placed in
										// front of the robot at the beginning
										// of a match
		driverAssist.correctRotation(SweetSpotMode.TOTE_WIDE); // Correct rotation in front of the
											// tote

		forkLift.nextToteLength(); // Pick up tote
		forkLift.nextToteLength(); // Go up one more so we can get the second
									// tote easily

		// Move backward slightly and then move in the X direction until we
		// reach the second yellow tote
		mecanumDrive.move(0, -1.0, 0);
		
		try 
		{
			Thread.sleep(100);
		} catch (InterruptedException e) {
			LogData.getInstance().record("ThreeYellowToteAuto.doAuto() - Error while sleeping " + e.getMessage());;
		}

		mecanumDrive.stop();
		mecanumDrive.move(90, -1.0, 0); // Move at a 90 degree angle to the
										// other tote

		// Wait until the previous tote gets out of vision
		try 
		{
			Thread.sleep(100);
		} catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (!detected) 
		{
			obstacleList = cameraAssist.getObjects();

			for (int i = 0; i <= obstacleList.size(); i++) 
			{
				if (obstacleList.get(i).type == ObjectType.TOTE) 
				{
					mecanumDrive.stop(); // If the next tote is in vision stop
					detected = true;
				}
			}
		}

		driverAssist.toteSweetSpot(SweetSpotMode.TOTE_WIDE); // get square with the tote and drive up
										// to it correctly

		forkLift.goToGround(); // Put tote back on the other tote and go back
								// down to the bottom position so we can lift
								// the whole stack up

		// Lift up the whole stack
		forkLift.nextBinLength();

		// Lift it up again so we can stack one more on top
		forkLift.nextBinLength();

		mecanumDrive.move(90, 1.0, 0); // Move at a 90 degree angle to the other
										// tote

		// Wait until the previous tote gets out of vision
		try 
		{
			Thread.sleep(100);
		} catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (!detected) 
		{
			obstacleList = cameraAssist.getObjects();

			for (int i = 0; i <= obstacleList.size(); i++) 
			{
				if (obstacleList.get(i).type == ObjectType.TOTE) 
				{
					mecanumDrive.stop(); // If the next tote is in vision stop
				}
			}
		}

		driverAssist.toteSweetSpot(SweetSpotMode.TOTE_WIDE); // get square with the tote and drive up
										// to it correctly

		forkLift.goToGround(); // Put tote back on the other tote and go back
								// down to the bottom position so we can lift
								// the whole stack up

		// Move backward into the autonomous zone
		driveIntoZone.doAuto();

		// Drop totes
		forkLift.goToGround();

	}

}
