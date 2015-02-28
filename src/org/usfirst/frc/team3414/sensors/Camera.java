package org.usfirst.frc.team3414.sensors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import org.usfirst.frc.team3414.autonomous.IVision;
import org.usfirst.frc.team3414.autonomous.ObjectColor;
import org.usfirst.frc.team3414.autonomous.ObjectType;
import org.usfirst.frc.team3414.autonomous.Obstacle;
import org.usfirst.frc.team3414.autonomous.ToteSide;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Camera implements IVision
{

	private boolean outputToDashboard = false;

	// Image Finding Constants and objects
	// private double LONG_RATIO = 2.22; //Tote long side = 26.9 / Tote height =
	// 12.1 = 2.22
	// private double SHORT_RATIO = 1.4; //Tote short side = 16.9 / Tote height
	// = 12.1 = 1.4
	// private double SCORE_MIN = 75.0; //Minimum score to be considered a tote
	private double SCORE_MIN = 40; // Minimum score to be considered a tote
	private double VIEW_ANGLE = 49.4; // camera view angle
	private NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(30, 50); // Default
																		// hue
																		// range
																		// for
																		// yellow
																		// tote
	private NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(60, 255); // Default
																			// saturation
																			// range
																			// for
																			// yellow
																			// tote
	private NIVision.Range TOTE_VAL_RANGE = new NIVision.Range(150, 255); // Default
																			// value
																			// range
																			// for
																			// yellow
																			// tote

	private NIVision.Range BIN_HUE_RANGE = new NIVision.Range(90, 140);
	private NIVision.Range BIN_SAT_RANGE = new NIVision.Range(40, 130);
	private NIVision.Range BIN_VAL_RANGE = new NIVision.Range(40, 130);

	// private NIVision.Range TOTE_R_RANGE = new NIVision.Range(0, 255); //
	// Default hue range for yellow tote
	// private NIVision.Range TOTE_G_RANGE = new NIVision.Range(0, 255); //
	// Default saturation range for yellow tote
	// private NIVision.Range TOTE_B_RANGE = new NIVision.Range(255, 255); //
	// Default value range for yellow tote

	private double AREA_MINIMUM = 0.5; // Default Area minimum for particle as
										// percentage of total image area

	private CameraServer server;
	private int session;

	// A structure to hold measurements of a particle
	public class ParticleReport implements Comparator<ParticleReport>,
			Comparable<ParticleReport>
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

	// Structure to represent the scores for the various tests used for target
	// identification
	public class Scores
	{
		double Area;
		double Aspect;
	}

	private Image frame;

	public Camera()
	{
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		server = CameraServer.getInstance();
		server.setQuality(50);
		
		//server.startAutomaticCapture("cam0");

		session = NIVision.IMAQdxOpenCamera("cam1",
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);

		// NIVision.ParticleFilterCriteria2 criteria[] = new
		// NIVision.ParticleFilterCriteria2[1];
		// NIVision.ParticleFilterOptions2 filterOptions = new
		// NIVision.ParticleFilterOptions2(0, 0, 1, 1);

		// Tote default thresholds
		if (outputToDashboard)
		{
			SmartDashboard.putNumber("MAX HUE_TOTE", 50);
			SmartDashboard.putNumber("MIN HUE_TOTE", 30);

			SmartDashboard.putNumber("MAX SAT_TOTE", 255);
			SmartDashboard.putNumber("MIN SAT_TOTE", 60);

			SmartDashboard.putNumber("MAX VALUE_TOTE", 255);
			SmartDashboard.putNumber("MIN VALUE_TOTE", 150);

			// Bin default thresholds
			SmartDashboard.putNumber("MAX HUE_BIN", 140);
			SmartDashboard.putNumber("MIN HUE_BIN", 90);

			SmartDashboard.putNumber("MAX SAT_BIN", 130);
			SmartDashboard.putNumber("MIN SAT_BIN", 40);

			SmartDashboard.putNumber("MAX VALUE_BIN", 130);
			SmartDashboard.putNumber("MIN VALUE_BIN", 40);
		}

	}

	private ToteSide isATote()
	{
		NIVision.IMAQdxGrab(session, frame, 1);

		// Error store for the particle filter
		int imaqError;

		// Binary image (unsigned int 8)
		Image binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 3);
		// Particle filter and other filter options
		NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
		criteria[0] = new NIVision.ParticleFilterCriteria2(
				NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM,
				100.0, 0, 0);
		NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(
				0, 0, 1, 1);

		// CameraServer.getInstance().setImage(frame);

		if (outputToDashboard)
		{
			TOTE_HUE_RANGE.maxValue = (int) SmartDashboard.getNumber("MAX HUE");
			TOTE_HUE_RANGE.minValue = (int) SmartDashboard.getNumber("MIN HUE");

			TOTE_SAT_RANGE.maxValue = (int) SmartDashboard.getNumber("MAX SAT");
			TOTE_SAT_RANGE.minValue = (int) SmartDashboard.getNumber("MIN SAT");

			TOTE_VAL_RANGE.maxValue = (int) SmartDashboard
					.getNumber("MAX VALUE");
			TOTE_VAL_RANGE.minValue = (int) SmartDashboard
					.getNumber("MIN VALUE");
		}

		// Threshold the image looking for yellow (tote color)
		NIVision.imaqColorThreshold(binaryFrame, frame, 255,
				NIVision.ColorMode.HSV, TOTE_HUE_RANGE, TOTE_SAT_RANGE,
				TOTE_VAL_RANGE);

		// Image morphFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 3);
		// StructuringElement structure = new StructuringElement(3, 3, 1);

		NIVision.imaqGrayMorphology(binaryFrame, binaryFrame,
				NIVision.MorphologyMethod.ERODE, null);

		// OUTPUT IMAGE TO SMART DASHBOARD
		// CameraServer.getInstance().setImage(binaryFrame);

		// filter out small particles
		float areaMin = (float) SmartDashboard.getNumber("Area min %",
				AREA_MINIMUM);
		criteria[0].lower = areaMin;

		imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame,
				criteria, filterOptions, null);

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

			if (outputToDashboard)
			{
				SmartDashboard.putNumber("Bounding Top",
						particles.get(largest_inx).BoundingRectTop);
				SmartDashboard.putNumber("Bounding Left",
						particles.get(largest_inx).BoundingRectLeft);
				SmartDashboard.putNumber("Bounding Right",
						particles.get(largest_inx).BoundingRectRight);
				SmartDashboard.putNumber("Bounding Bottom",
						particles.get(largest_inx).BoundingRectBottom);
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
			// SmartDashboard.putNumber("Aspect", scores.Aspect);
			scores.Area = AreaScore(particles.elementAt(largest_inx));
			// SmartDashboard.putNumber("Area", scores.Area);

			if (outputToDashboard)
			{
				SmartDashboard.putNumber("Tote Score Area", scores.Area);
				SmartDashboard.putNumber("Tote Score Aspect", scores.Aspect);
			}

			// return scores.Aspect > SCORE_MIN && scores.Area > SCORE_MIN;

			if (isItTheFront(particles.elementAt(largest_inx)))
			{
				if (outputToDashboard)
				{
					SmartDashboard.putBoolean("Is Tote Side", false);
					SmartDashboard.putBoolean("Is Tote Front", true);
				}

				return ToteSide.FRONT;
			} else if (isItTheSide(particles.elementAt(largest_inx)))
			{
				if (outputToDashboard)
				{
					SmartDashboard.putBoolean("Is Tote Side", true);
					SmartDashboard.putBoolean("Is Tote Front", false);
				}

				return ToteSide.SIDE;
			} else
			{
				if (outputToDashboard)
				{
					SmartDashboard.putBoolean("Is Tote Side", false);
					SmartDashboard.putBoolean("Is Tote Front", false);
				}

				return ToteSide.NONE;
			}

		} else
		{

			if (outputToDashboard)
			{
				SmartDashboard.putBoolean("Is Tote Side", false);
				SmartDashboard.putBoolean("Is Tote Front", false);
			}

			return ToteSide.NONE;
		}

	}

	private boolean isABin()
	{
		NIVision.IMAQdxGrab(session, frame, 1);

		// Error store for the particle filter
		int imaqError;

		// Binary image (unsigned int 8)
		Image binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 3);
		// Particle filter and other filter options
		NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
		criteria[0] = new NIVision.ParticleFilterCriteria2(
				NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM,
				100.0, 0, 0);
		NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(
				0, 0, 1, 1);

		// CameraServer.getInstance().setImage(frame);

		if (outputToDashboard)
		{
			BIN_HUE_RANGE.maxValue = (int) SmartDashboard
					.getNumber("MAX HUE_BIN");
			BIN_HUE_RANGE.minValue = (int) SmartDashboard
					.getNumber("MIN HUE_BIN");

			BIN_SAT_RANGE.maxValue = (int) SmartDashboard
					.getNumber("MAX SAT_BIN");
			BIN_SAT_RANGE.minValue = (int) SmartDashboard
					.getNumber("MIN SAT_BIN");

			BIN_VAL_RANGE.maxValue = (int) SmartDashboard
					.getNumber("MAX VALUE_BIN");
			BIN_VAL_RANGE.minValue = (int) SmartDashboard
					.getNumber("MIN VALUE_BIN");
		}

		// Threshold the image looking for yellow (tote color)
		NIVision.imaqColorThreshold(binaryFrame, frame, 255,
				NIVision.ColorMode.HSV, BIN_HUE_RANGE, BIN_SAT_RANGE,
				BIN_VAL_RANGE);

		// Image morphFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 3);
		// StructuringElement structure = new StructuringElement(3, 3, 1);

		NIVision.imaqGrayMorphology(binaryFrame, binaryFrame,
				NIVision.MorphologyMethod.CLOSE, null);

		// OUTPUT IMAGE TO SMART DASHBOARD
		// CameraServer.getInstance().setImage(binaryFrame);

		// filter out small particles
		float areaMin = (float) SmartDashboard.getNumber("Area min %",
				AREA_MINIMUM);
		criteria[0].lower = areaMin;

		imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame,
				criteria, filterOptions, null);

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

			if (outputToDashboard)
			{
				SmartDashboard.putNumber("Bounding Top",
						particles.get(largest_inx).BoundingRectTop);
				SmartDashboard.putNumber("Bounding Left",
						particles.get(largest_inx).BoundingRectLeft);
				SmartDashboard.putNumber("Bounding Right",
						particles.get(largest_inx).BoundingRectRight);
				SmartDashboard.putNumber("Bounding Bottom",
						particles.get(largest_inx).BoundingRectBottom);
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
			// SmartDashboard.putNumber("Aspect", scores.Aspect);
			scores.Area = AreaScore(particles.elementAt(largest_inx));
			// SmartDashboard.putNumber("Area", scores.Area);

			if (outputToDashboard)
			{
				SmartDashboard.putNumber("Tote Score Area", scores.Area);
				SmartDashboard.putNumber("Tote Score Aspect", scores.Aspect);
			}

			double binRectangularity = rectangularity(particles
					.elementAt(largest_inx));

			if (outputToDashboard)
			{
				SmartDashboard.putNumber("Bin Rectangularity",
						binRectangularity);
			}

			if (binRectangularity > 0.95 && binRectangularity < 1.65)
			{
				return true;
			} else
			{
				return false;
			}

		} else
		{

			return false;
		}

	}

	// Comparator function for sorting particles. Returns true if particle 1 is
	// larger
	// private static boolean CompareParticleSizes(ParticleReport
	// particle1,ParticleReport particle2)
	// {
	// we want descending sort order
	// return particle1.PercentAreaToImageArea >
	// particle2.PercentAreaToImageArea;
	// }

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

	/*
	 * Is it the front of the yellow box
	 */
	private boolean isItTheFront(ParticleReport report)
	{
		if (outputToDashboard)
		{
			SmartDashboard.putNumber("Rectangularity", rectangularity(report));
		}

		if (rectangularity(report) > 0.7 && rectangularity(report) < 0.95)
		{
			return true;
		} else
		{
			return false;
		}
	}

	private double rectangularity(ParticleReport report)
	{
		return (report.BoundingRectBottom - report.BoundingRectTop)
				/ (report.BoundingRectRight - report.BoundingRectLeft);
	}

	/*
	 * Is it the side of the yellow box
	 */
	private boolean isItTheSide(ParticleReport report)
	{
		if (rectangularity(report) > 0.3 && rectangularity(report) < 0.6)
		{
			return true;
		} else
		{
			return false;
		}
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
		normalizedWidth = 2
				* (report.BoundingRectRight - report.BoundingRectLeft)
				/ size.width;
		targetWidth = 7;

		return targetWidth
				/ (normalizedWidth * 12 * Math.tan(VIEW_ANGLE * Math.PI
						/ (180 * 2)));
	}

	ToteSide toteDetect;
	boolean binDetect;
	List<Obstacle> obstacleList;

	@Override
	public List<Obstacle> getObjects()
	{
		obstacleList = new ArrayList<>();

		toteDetect = isATote();
		if (toteDetect == ToteSide.FRONT)
		{
			Obstacle tote = new Obstacle();
			tote.color = ObjectColor.YELLOW;
			tote.type = ObjectType.TOTE;
			tote.side = ToteSide.FRONT;

			obstacleList.add(tote);
		} else if (toteDetect == ToteSide.SIDE)
		{
			Obstacle tote = new Obstacle();
			tote.color = ObjectColor.YELLOW;
			tote.type = ObjectType.TOTE;
			tote.side = ToteSide.SIDE;

			obstacleList.add(tote);
		}

		binDetect = isABin();
		if (binDetect)
		{
			Obstacle tote = new Obstacle();
			tote.color = ObjectColor.GREEN;
			tote.type = ObjectType.TRASHCAN;
			tote.side = ToteSide.NONE;

			obstacleList.add(tote);
		}

		return obstacleList;
	}

	public boolean isInAutoZone()
	{
		// TODO Auto-generated method stub
		return false;
	}
}
