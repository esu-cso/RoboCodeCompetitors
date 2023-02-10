package 2023Spring;
import robocode.*;
import java.awt.Color;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Bog_Bog_Destroyer_of_Chumps - a robot by Ben Chernin
 */
public class Bog_Bog_Destroyer_of_Chumps extends AdvancedRobot
{
	boolean movingForward;
	/**
	 * run: Bog_Bog_Destroyer_of_Chumps's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here
		setBodyColor(Color.RED);
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:
		
		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(80);
			movingForward = true;
			setTurnRight(90);
			turnGunLeft(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(10);
		fire(10);
	}

	public void reverseDirection() {
		if (movingForward) {
			setBack(40000);
			movingForward = false;
		} else {
			setAhead(40000);
			movingForward = true;
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(60);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		reverseDirection();
	}	
}
