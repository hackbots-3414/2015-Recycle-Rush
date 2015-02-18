package org.usfirst.frc.team3414.sensors;

public class SPI implements ISPI
{

	edu.wpi.first.wpilibj.SPI spi;
	
	public SPI(edu.wpi.first.wpilibj.SPI.Port SPIport) {
		spi = new edu.wpi.first.wpilibj.SPI(SPIport);
	}

	@Override
	public void setClockRate(int clockRate) {
		spi.setClockRate(clockRate);
	}

	@Override
	public void setMSBFirst() {
		spi.setMSBFirst();
	}

	@Override
	public void setLSBFirst() {
		spi.setLSBFirst();
	}

	@Override
	public void setChipSelectActiveLow() {
		spi.setChipSelectActiveLow();
	}

	@Override
	public void setChipSelectActiveHigh() {
		spi.setChipSelectActiveHigh();
	}

	@Override
	public void transaction(byte[] dataToSend, byte[] dataToRecieve, int size) {
		spi.transaction(dataToSend, dataToRecieve, size);
	}

}
