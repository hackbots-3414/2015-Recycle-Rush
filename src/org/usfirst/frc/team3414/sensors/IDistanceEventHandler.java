package org.usfirst.frc.team3414.sensors;

public interface IDistanceEventHandler 
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public long addListener(IMeasureDistanceListener listener, IMeasureDistance sensor, Range distance);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public long addListener(IMeasureDistanceListener listener,IMeasureDistance sensor, Range distance, boolean repeat);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void removeListener(long distanceEventID) ;
	
	
}
