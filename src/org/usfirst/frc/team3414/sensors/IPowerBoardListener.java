package org.usfirst.frc.team3414.sensors;



/**
 *
 *This interface is designed to be implemented by any class that needs to respond to voltage or amperage events.
 *
 * @generated
 */
public  interface IPowerBoardListener 
{
	/**
	 * 
	 * An event which is triggered when the voltage dips below or rises above a certain value.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public void voltageEvent(PowerEventArgs voltageEventArgs) ;
	
	/**
	 * 
	 * An event which is triggered when the current dips below or rises above a certain value
	 * 
	 * @generated
	 * @ordered
	 */
	
	public void amperageEvent(PowerEventArgs amperageEventArgs) ;
	
	
}

