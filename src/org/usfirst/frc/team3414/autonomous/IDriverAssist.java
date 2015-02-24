package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.sensors.SweetSpotMode;


public  interface IDriverAssist 
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void avoidSideDebris() ;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void binSweetSpot(SweetSpotMode mode) ;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void correctRotation(SweetSpotMode mode) ;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void toteSweetSpot(SweetSpotMode mode) ;
	
	
}

