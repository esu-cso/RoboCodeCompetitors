package 2023Spring;
import robocode.*;
//import java.util.Random;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * JohnBot - a robot by John Winward
 */
public class JohnBot extends Robot
{
	/**
	 * run: TestBot's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here
		double distance = 300.0;
		double angle = 90.0;
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForRobotTurn(true);
		setAdjustRadarForGunTurn(true);

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like		
			
			//Start moving
			ahead(distance);
			
			//Scan, fire when robot sighted
			for(int i = 0; i < 50; i++){
				turnRadarRight(10.0);
				turnGunRight(10.0);
			}
			
			//turnGunRight(360.0);
			//turnRadarRight(360.0);
			
			//Rotate
			turnRight(angle);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		turnGunRight(10.0);
		stop();
		fire(Rules.MAX_BULLET_POWER);
		resume();
		turnGunLeft(10.0);
		
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		//back(50);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(100);
	}	
}
