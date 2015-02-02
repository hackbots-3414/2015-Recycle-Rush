package org.usfirst.frc.team3414.sensors;
import org.usfirst.frc.team3414.sensors.ITimer;
import org.usfirst.frc.team3414.teleop.Log;
import org.usfirst.frc.team3414.sensors.ITimeListener;
import org.usfirst.frc.team3414.teleop.TeleopControl;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class VirutalClock implements ITimer
{
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
	
	public TeleopControl teleopControl;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public VirutalClock(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public long addListenerSec(ITimeListener listener, boolean continuous, long timeToExtendSec) {
		// TODO implement me
		return 0L;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void removeListener(long eventID) {
		// TODO implement me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public long getTimeInMinutes() {
		// TODO implement me
		return 0L;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public long getTimeInSeconds() {
		// TODO implement me
		return 0L;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public long getTimeInMillis() {
		// TODO implement me
		return 0L;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public long addListenerMS(ITimeListener listener, boolean continuous, long timeToExtendMS) {
		// TODO implement me
		return 0L;	
	}
	
}

