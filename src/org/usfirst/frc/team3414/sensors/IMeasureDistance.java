package org.usfirst.frc.team3414.sensors;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public  interface IMeasureDistance 
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public long addListener(IMeasureDistanceListener listener, long distance) ;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public double getCm() ;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  --
	 * @generated
	 * @ordered
	 */
	
	public double getFeet() ;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public double getInches() ;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void removeListener(long distanceEventID) ;
	
	
}

