package org.usfirst.frc.team3414.autonomous;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.sensors.Camera;
import org.usfirst.frc.team3414.sensors.IDetectLines;
import org.usfirst.frc.team3414.sensors.ISwitch;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class AutonomousControl
{	
	public ISwitch iAutonomousSwitches;
	
	public IDriverAssist iDriverAssist;
	
	public IDetectObjects iDetectObjects;
	
	public IDetectLines iDetectLines;
	
	public ILiftAssist iLiftAssist;
	
	public Camera cameraAssist = new Camera();
	
	AutonomousProcedure driveIntoAuto = new DriveIntoAuto(); // Creates all of the autonomouses that will be used.
	AutonomousProcedure oneYellowToteAuto = new OneYellowToteAuto();
	AutonomousProcedure twoYellowToteAuto = new TwoYellowToteAuto();
	AutonomousProcedure threeYellowToteAuto = new ThreeYellowToteAuto();
	AutonomousProcedure oneRecycleBinAuto = new OneRecycleBinAuto();
	AutonomousProcedure twoRecycleBinAuto = new TwoRecycleBinAuto();
	
	/**
	 * Constructs the Autonomous Control object.
	 */
	public AutonomousControl(){
		super();
	}


	/**
	 * Calls the autonomous selected by the inputed AutonomousType, usually from the counter switch on the robot.
	 * 
	 * @param AutonomousType
	 */
	public void doAuto(AutonomousMode AutonomousType) 
	{
		// If nothing is passed in then we will just not do anything
		if(AutonomousType == null)
		{
			return;
		}
		
		// Each autonomous if loop calls it's respective autonomous procedure
		if(AutonomousType == AutonomousMode.DRIVEINTOAUTO)
		{
			driveIntoAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.NOTHING)
		{
			return;
		}
		
		if(AutonomousType == AutonomousMode.YELLOWTOTE1)
		{
			oneYellowToteAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.YELLOWTOTE2)
		{
			twoYellowToteAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.YELLOWTOTE3)
		{
			threeYellowToteAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.RECYCLECAN1)
		{
			oneRecycleBinAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.RECYCLECAN2)
		{
			twoRecycleBinAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.GREYTOTES)
		{
			// TODO: implement grey tote autonomous
		}
	}
	
}

