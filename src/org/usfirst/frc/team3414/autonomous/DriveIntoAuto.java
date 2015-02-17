//package org.usfirst.frc.team3414.autonomous;
//
//import org.usfirst.frc.team3414.actuators.IDriveTrain;
//import org.usfirst.frc.team3414.actuators.MecanumDrive;
//import org.usfirst.frc.team3414.sensors.Camera;
//import org.usfirst.frc.team3414.sensors.ITimeListener;
//import org.usfirst.frc.team3414.sensors.TimeEventArgs;
///**
// * An autonomous routine that drives into the autonomous zone and does nothing else
// * 
// * @author Ray
// *
// */
//public class DriveIntoAuto implements AutonomousProcedure, ITimeListener
//{
//	Camera cameraAssist = new Camera(); // Should be singleton
//	//TimeElapsedEventArgs args = new TimeElapsedEventArgs(0, 0, 0);
//	//VirtualClock clock = new VirtualClock(args); // Fix instantiation later
//	
//	IDriveTrain mecanumDrive = MecanumDrive.getInstance();
//	
//	public void doAuto()
//	{
//		mecanumDrive.move(0, 1.0, 0.0); // Move forward into the autonomous zone
//		// TODO: add listener
//		//clock.addListenerSec(this, false, 3); //Change for timeout value (this sets the value where the robot gives up on finding the line and just stops by timebase)
//		
//		while(cameraAssist.areWeInAutoZone() == false)
//		{
//			
//		}
//		
//		MecanumDrive.getInstance().stop(); // Stops when we get into the autonomous zone
//	}
//
//	@Override
//	public void timeEvent(TimeEventArgs timeEvent) {
//		MecanumDrive.getInstance().stop();
//	}
//
//}
