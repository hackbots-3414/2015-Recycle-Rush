package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 * Camera settings, called in robot init.
 *
 * @author ray
 */
public class EthernetCamera
{

    private AxisCamera robotCamera;

    /**
     * Initialize the camera.
     * 
     */
    public void init()
    {
        robotCamera = new AxisCamera("cam3");

        // To be replaced with image processing code
        // Sets axis camera stuff at the beginning of the robot
        robotCamera.writeMaxFPS(15);
        robotCamera.writeCompression(70);
        robotCamera.writeColorLevel(100);
        robotCamera.writeResolution(AxisCamera.Resolution.k320x240);
        robotCamera.writeBrightness(50);
    }

}
