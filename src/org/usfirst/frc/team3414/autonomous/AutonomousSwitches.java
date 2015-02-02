package org.usfirst.frc.team3414.autonomous;
import org.usfirst.frc.team3414.teleop.Log;
import org.usfirst.frc.team3414.teleop.Display;
import org.usfirst.frc.team3414.sensors.ISwitch;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class AutonomousSwitches implements ISwitch
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Display display;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Log log;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public AutonomousControl autonomousControl;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public AutonomousSwitches(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public SwitchPositions getPosition() {
		// TODO implement me
		return SwitchPositions.ON;	
	}
	
}

