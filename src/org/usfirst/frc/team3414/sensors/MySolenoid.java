package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.Solenoid;

public class MySolenoid implements ISolenoid
{
    Solenoid solenoid;
    
    public MySolenoid(int channel)
    {
	solenoid = new Solenoid(channel);
    }

    public void set(boolean on)
    {
	solenoid.set(on);
    }

    public boolean get()
    {
	return solenoid.get();
    }

}
