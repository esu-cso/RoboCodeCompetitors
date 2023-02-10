package 2023Spring;

import robocode.*;
import java.util.Random;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * NEPTR - a robot by (Nathan)
 */
public class NEPTR extends Robot {
	/**
	 * run: NEPTR's default behavior
	 */
	Random random = new Random();
	int height = 400;
	int width = 300;
	boolean whichWay = random.nextBoolean();
	boolean howFar = random.nextBoolean();
	boolean turnAtAll = random.nextBoolean();
	boolean whichDirection = random.nextBoolean();
	int dividend = random.nextInt(3) + 1;
	int balls = random.nextInt(100) + 45;
	int superBalls = random.nextInt(180) + 90;
	int power = 1;

	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		setColors(Color.gray, Color.blue, Color.cyan); // body,gun,radar

		// Robot main loop
		while (true) {
			moveCompletelyRandomly();
			// turnDirection(whichDirection, superBalls);
			// ahead(1000000);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(power);
		turnGunRight(45);
		turnGunLeft(100);
		fire(power);

		if (e.getDistance() < 30)
			back(60);

	}

	public void onHitRobot(HitRobotEvent e) {
		turnDirection(whichDirection, superBalls);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		int maybe = random.nextInt(2) + 1;
		int lol = random.nextInt(90) + 1;

		turnDirection(whichDirection, superBalls);

		switch (maybe) {
			case 1:
				ahead(lol);
				break;
			case 2:
				back(lol);
				break;

		}
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		Random random = new Random();

		turnDirection(whichDirection, superBalls);

		if (howFar == true)
			ahead((height / dividend));
		else
			ahead((width / dividend));
	}

	public void shootThem() {
		// ahead(50);
		turnGunRight(45);
		turnGunLeft(45);
		turnGunRight(90);
	}

	public void moveCompletelyRandomly() {
		Random random = new Random();
		int i = random.nextInt(1000);
		int o = random.nextInt(12);
		// int j = random.nextInt(200);
		int j = 1;

		switch (o) {
			case 0:
				if (turnAtAll == true)

					turnDirection(true, ((width / dividend) * dividend));
				else
					ahead((width / dividend) * dividend);

				// if (turnAtAll = true) 
				// turnDirection();
				break;
			case 1:
				turnRight(balls);
				break;
			case 2:
				if (turnAtAll == true)

					turnDirection(true, ((height / dividend) * dividend));

				ahead((height / dividend) * dividend);

				// if (turnAtAll = true)
				// turnDirection();
				break;
			case 3:
				turnLeft(balls);
				break;
			case 4:
				if (turnAtAll == true)
					turnDirection(false, ((height / dividend) * dividend));

				back((height / dividend) * dividend);

				// if (turnAtAll = true)
				// turnDirection();
				break;
			case 5:
				turnGunLeft(180);
				break;
			case 6:
				if (turnAtAll == true)
					turnDirection(false, ((width / dividend) * dividend));

				back((width / dividend) * dividend);

				// if (turnAtAll = true)
				// turnDirection();
				break;
			case 7:
				turnGunRight(180);
				break;
			case 8:
				turnGunLeft(j);
				break;
			case 9:
				turnGunRight(j);
				break;
			case 10:
				if (turnAtAll == true)
					turnDirection(true, (width / j));

				ahead(width / j);

				// if (turnAtAll = true)
				// turnDirection();
				break;
			case 11:
				if (turnAtAll == true)
					turnDirection(false, (height / j));

				back(height / j);

				// if (turnAtAll = true)
				// turnDirection();
				break;
			case 12:
				ahead(400);
				turnRight(90);
				break;
		}
	}

	public void turnDirection(boolean forwardOrBack, int distance) {
		if (forwardOrBack == true) {
			ahead(distance);
			if (whichWay == true)
				turnRight(superBalls);
			else
				turnLeft(superBalls);
		} else if (forwardOrBack == false) {
			back(distance);
			if (whichWay == true)
				turnRight(superBalls);
			else
				turnLeft(superBalls);
		}

		if (whichWay == true)
			turnRight(superBalls);
		else
			turnLeft(superBalls);

	}
}
