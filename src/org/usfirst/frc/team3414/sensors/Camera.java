package org.usfirst.frc.team3414.sensors;

import java.util.Comparator;

import org.usfirst.frc.team3414.autonomous.IDetectObjects;
import org.usfirst.frc.team3414.autonomous.ObjectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3414.autonomous.Obstacle;

public class Camera implements IDetectObjects 
{	
	// Image Finding Constants and objects
	//private double LONG_RATIO = 2.22; //Tote long side = 26.9 / Tote height = 12.1 = 2.22
	//private double SHORT_RATIO = 1.4; //Tote short side = 16.9 / Tote height = 12.1 = 1.4
	private double SCORE_MIN = 75.0;  //Minimum score to be considered a tote
	private double VIEW_ANGLE = 49.4; //camera view angle
	private NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(101, 64); // Default hue range for yellow tote
	private NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(88, 255); // Default saturation range for yellow tote
	private NIVision.Range TOTE_VAL_RANGE = new NIVision.Range(134, 255); // Default value range for yellow tote
	private double AREA_MINIMUM = 0.5; // Default Area minimum for particle as percentage of total image area
	
	private CameraServer server;
	private int session;
	private Image frame;
	
	// A structure to hold measurements of a particle
	public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport> 
	{
		private double PercentAreaToImageArea;
		private double Area;
		private double BoundingRectLeft;
		private double BoundingRectTop;
		private double BoundingRectRight;
		private double BoundingRectBottom;

		public int compareTo(ParticleReport r) 
		{
			return (int) (r.Area - this.Area);
		}

		public int compare(ParticleReport r1, ParticleReport r2) 
		{
			return (int) (r1.Area - r2.Area);
		}
	}

	// Structure to represent the scores for the various tests used for target identification
	public class Scores 
	{
		double Area;
		double Aspect;
	}

	public Camera() 
	{
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture("cam0");

		NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);
		NIVision.IMAQdxStartAcquisition(session);

		//NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
		//NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);
	}

	private boolean isATote() 
	{
		// Error store for the particle filter
		int imaqError;
		
		// Binary image (unsigned int 8)
		Image binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		// Particle filter and other filter options
		NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0);
		NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);
	

		// Threshold the image looking for yellow (tote color)
		NIVision.imaqColorThreshold(binaryFrame, frame, 255, NIVision.ColorMode.HSV, TOTE_HUE_RANGE, TOTE_SAT_RANGE, TOTE_VAL_RANGE);
		// filter out small particles
		float areaMin = (float) SmartDashboard.getNumber("Area min %", AREA_MINIMUM);
		criteria[0].lower = areaMin;
		imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);

		int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		
		if (numParticles > 0) 
		{
			// Measure particles and sort by particle size
			Vector<ParticleReport> particles = new Vector<ParticleReport>();

			// Variables to keep the largest particle known
			int largest_inx = -1;
			double max_Area = 0;

			for (int particleIndex = 0; particleIndex < numParticles; particleIndex++) 
			{
				ParticleReport par = new ParticleReport();
				par.PercentAreaToImageArea = NIVision.imaqMeasureParticle(
						binaryFrame, particleIndex, 0,
						NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
				par.Area = NIVision.imaqMeasureParticle(binaryFrame,
						particleIndex, 0, NIVision.MeasurementType.MT_AREA);
				par.BoundingRectTop = NIVision.imaqMeasureParticle(binaryFrame,
						particleIndex, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
				par.BoundingRectLeft = NIVision.imaqMeasureParticle(
						binaryFrame, particleIndex, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				par.BoundingRectBottom = NIVision.imaqMeasureParticle(
						binaryFrame, particleIndex, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
				par.BoundingRectRight = NIVision.imaqMeasureParticle(
						binaryFrame, particleIndex, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
				particles.add(par);

				// Keeps the largest particle known
				if (par.Area > max_Area) 
				{
					largest_inx = particleIndex;
					max_Area = par.Area;
				}
			}

			// This example only scores the largest particle. Extending to score
			// all particles and choosing the desired one is left as an exercise
			// for the reader. Note that this scores and reports information
			// about a single particle (single L shaped target). To get accurate
			// information
			// about the location of the tote (not just the distance) you will
			// need to correlate two adjacent targets in order to find the true
			// center of the tote.
			Scores scores = new Scores();
			scores.Aspect = AspectScore(particles.elementAt(largest_inx));
			//SmartDashboard.putNumber("Aspect", scores.Aspect);
			scores.Area = AreaScore(particles.elementAt(largest_inx));
			//SmartDashboard.putNumber("Area", scores.Area);
			return scores.Aspect > SCORE_MIN && scores.Area > SCORE_MIN;
			
		} else {
			//SmartDashboard.putBoolean("IsTote", false);
			return false;
		}

	}

	// Comparator function for sorting particles. Returns true if particle 1 is
	// larger
	//private static boolean CompareParticleSizes(ParticleReport particle1,ParticleReport particle2) 
	//{
		// we want descending sort order
	//	return particle1.PercentAreaToImageArea > particle2.PercentAreaToImageArea;
	//}

	/**
	 * Converts a ratio with ideal value of 1 to a score. The resulting function
	 * is piecewise linear going from (0,0) to (1,100) to (2,0) and is 0 for all
	 * inputs outside the range 0-2
	 */
	private double ratioToScore(double ratio) 
	{
		return (Math.max(0, Math.min(100 * (1 - Math.abs(1 - ratio)), 100)));
	}

	private double AreaScore(ParticleReport report) 
	{
		double boundingArea = (report.BoundingRectBottom - report.BoundingRectTop)
				* (report.BoundingRectRight - report.BoundingRectLeft);
		// Tape is 7" edge so 49" bounding rect. With 2" wide tape it covers 24"
		// of the rect.
		return ratioToScore((49 / 24) * report.Area / boundingArea);
	}

	/**
	 * Method to score if the aspect ratio of the particle appears to match the
	 * retro-reflective target. Target is 7"x7" so aspect should be 1
	 */
	private double AspectScore(ParticleReport report) 
	{
		return ratioToScore(((report.BoundingRectRight - report.BoundingRectLeft) / (report.BoundingRectBottom - report.BoundingRectTop)));
	}

	/**
	 * Computes the estimated distance to a target using the width of the
	 * particle in the image. For more information and graphics showing the math
	 * behind this approach see the Vision Processing section of the
	 * ScreenStepsLive documentation.
	 *
	 * @param image
	 *            The image to use for measuring the particle estimated
	 *            rectangle
	 * @param report
	 *            The Particle Analysis Report for the particle
	 * @param isLong
	 *            Boolean indicating if the target is believed to be the long
	 *            side of a tote
	 * @return The estimated distance to the target in feet.
	 */
	private double computeDistance(Image image, ParticleReport report) 
	{
		double normalizedWidth, targetWidth;
		NIVision.GetImageSizeResult size;

		size = NIVision.imaqGetImageSize(image);
		normalizedWidth = 2	* (report.BoundingRectRight - report.BoundingRectLeft) / size.width;
		targetWidth = 7;

		return targetWidth / (normalizedWidth * 12 * Math.tan(VIEW_ANGLE * Math.PI / (180 * 2)));
	}

	public List<Obstacle> getObjects() {
		List<Obstacle> objectList = new ArrayList<>();
		Obstacle obstacle = new Obstacle();
		
		if(isATote()) obstacle.type = ObjectType.TOTE;

		objectList.add(obstacle);
		
		return objectList;
	}
	
	public boolean areWeInAutoZone()
	{
		return false;
		
	}
}
