package org.usfirst.frc.team3414.actuators;

import edu.wpi.first.wpilibj.SpeedController;

public class InverseController implements SpeedController
{
    SpeedController controller;
    public InverseController(SpeedController controller)
    {
	super();
	this.controller = controller;
    }

    public void pidWrite(double output)
    {
	controller.pidWrite(output);
    }

    public double get()
    {
	return controller.get() * -1;
    }

    public void set(double speed, byte syncGroup)
    {
	controller.set(speed * -1, syncGroup);
    }

    public void set(double speed)
    {
	controller.set(speed * -1);
	
    }

    public void disable()
    {
	controller.disable();
	
    }
    
}
