package org.usfirst.frc.team3414.sensors;
import org.usfirst.frc.team3414.teleop.Log;
import org.usfirst.frc.team3414.teleop.Display;
import org.usfirst.frc.team3414.autonomous.AutonomousControl;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class LimitSwitch implements ISwitch
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
	
	public AutonomousControl autonomousControl;
	
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
	 */
	public LimitSwitch(){
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

