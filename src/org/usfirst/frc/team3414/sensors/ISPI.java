package org.usfirst.frc.team3414.sensors;

public interface ISPI {
	
	public void setClockRate(int clockRate);
	
	public void setMSBFirst();
	
	public void setLSBFirst();
	
	public void setChipSelectActiveLow();
	
	public void setChipSelectActiveHigh();
	
	public int transaction(byte[] dataToSend, byte[] dataToRecieve , int size);
}
