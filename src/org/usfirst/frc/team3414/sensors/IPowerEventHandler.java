package org.usfirst.frc.team3414.sensors;



/**
 * 
 * This interface is designed to be implemented by a class which can add and remove voltage and amperage listeners.
 * 
 * @generated
 */
public  interface IPowerEventHandler 
{
    
    /**
	 * 
	 * Triggers an event when a voltage threshold is passed.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public long addVoltageListener(IPowerEventListener listener, PowerThreshold threshold, int channel, boolean repeat) ;
	
	/**
	 * 
	 * Triggers an event when a current threshold is passed.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public long addCurrentListener(IPowerEventListener listener, PowerThreshold highThreshold, int channel, boolean repeat) ;
	/**
	 * 
	 * Triggers an event when a voltage threshold is passed.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public long addVoltageListener(IPowerEventListener listener, PowerThreshold threshold, int channel) ;
	
	/**
	 * 
	 * Triggers an event when a current threshold is passed.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public long addCurrentListener(IPowerEventListener listener, PowerThreshold highThreshold, int channel) ;
	
	/**
	 * 
	 * Removes a voltage listener.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public void removeVoltageListener(long eventId) ;
	
	/**
	 * 
	 * Removes a current listener.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public void removeCurrentListener(long eventId) ;
	
	
}

