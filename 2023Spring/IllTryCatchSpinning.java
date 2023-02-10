package 2023Spring;
import robocode.*;
import java.awt.Color;

/**
 * IllTryCatchSpinning - a robot by Logan Keim
 * I am so sorry to anyone reading this. This code is a mess. I had a ton more I wanted to write, but I ran out of time.
 */
public class IllTryCatchSpinning extends AdvancedRobot
{
	/**
	 * run: LoganKBot's default behavior
	 */
	int roboMode = -1;
	int scanIntro = 0;
	int c = 0;
	double h = 0;
	double w = 0;
	double h2 = 0;
	double w2 = 0;
	double roboW = 0;
	double roboH = 0;
	double roboHead = 0;
	double fromCenterH = 0;
	double fromCenterW = 0;
	int dir = 1;

	double[][] cornerC = new double[2][2];
	double[][] enemyCoords = {{0, 0}, {0, 0}};

	double turnGun;

	public void run() {
		// Initialization of the robot should be put here
		Color pink = new Color(254, 13, 167);
		Color blue = new Color(0, 56, 167);
		Color purple = new Color(155, 79, 151);
		Color green = new Color(0, 161, 155);
		Color cyan = new Color(0, 255, 255);

		setColors(pink, green, blue, purple, cyan);

		setAdjustGunForRobotTurn(true);
		//setAdjustRadarForGunTurn(true);

		h = getBattleFieldHeight();
		w = getBattleFieldWidth();
		h2 = h / 2;
		w2 = w / 2;
		roboW = getX();
		roboH = getY();
		roboHead = getHeading();
		fromCenterH = h2 - roboH;
		fromCenterW = w2 - roboW;
		
		int err = 1;

		while(err == 1)
		{
			err = 0;

			if(fromCenterH < 0)
			{
				if(fromCenterW < 0)
				{
					//Bottom left
					cornerC[0][0] = h;
					cornerC[0][1] = 0;
					cornerC[1][0] = 0;
					cornerC[1][1] = w;
				}	
				else if(fromCenterW > 0)
				{
					//Bottom right
					cornerC[0][0] = 0;
					cornerC[0][1] = 0;
					cornerC[1][0] = h;
					cornerC[1][1] = w;
				}
				else
				{
					//Error
					ahead(10);
					err = 1;
				}
			}
			else if(fromCenterH > 0)
			{
				if(fromCenterW < 0)
				{
					//Top left
					cornerC[0][0] = h;
					cornerC[0][1] = w;
					cornerC[1][0] = 0;
					cornerC[1][1] = 0;
				}	
				else if(fromCenterW > 0)
				{
					//Top right
					cornerC[0][0] = 0;
					cornerC[0][1] = w;
					cornerC[1][0] = h;
					cornerC[1][1] = 0;
				}
				else
				{
					//Error
					ahead(10);
					err = 1;
				}
			}
			else
			{
				//Error
				if(getVelocity() == 0)
				{	
					ahead(10 * dir);
				}
				err = 1;
			}
		}

		err = 1;

		//Later, add logic to determine which corners to sweep from. For now, we'll do a 360 sweep
		/*do
		{
			turnGunRight(360);
			if(scanIntro == 1)
			{
				//One v one mode!
				roboMode = 0;
			}
			else if(scanIntro >= 1)
			{
				//Battle royale mode!
				roboMode = 1;
			}
			else if(scanIntro == 0)
			{
				err = 0;
			}
		} while(err == 0);*/

		setTurnGunRight(360);
		//setTurnRadarRight(360);
		roboMode = 1;

		execute();

		// Robot main loop
		while(true)
		{
			setTurnGunRight(turnGun);
			//setTurnRadarRight(turnGun);
			c++;
			if(c > 3)
			{
				turnGun = 15;
			}

			if(c > 6)
			{
				turnGun = -15;
			}

			setAhead(30 * dir);

			if(getX() <= 10 || getX() >= (w - 10) || getY() <= 10 || getY() >= (h - 10))
			{
				dir = dir * -1;
			}

			execute();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		c = 0;

		setTurnRight(e.getBearing() + 90);
		
		double radAngle = Math.toRadians(getHeading() + e.getBearing() % 360);
		if(enemyCoords[0][0] == 0)
		{
			//If this is the first time tracking the enemy
			enemyCoords[0][0] = (getX() + Math.sin(radAngle) * e.getDistance());
			enemyCoords[0][1] = (getY() + Math.cos(radAngle) * e.getDistance());
		}
		else
		{
			enemyCoords[1][0] = enemyCoords[0][0];
			enemyCoords[1][1] = enemyCoords[0][1];
			enemyCoords[0][0] = (getX() + Math.sin(radAngle) * e.getDistance());
			enemyCoords[0][1] = (getY() + Math.cos(radAngle) * e.getDistance());
		}

		if(roboMode == -1)
		{
			scanIntro++;
			if(getGunHeat() == 0)
			{
				fire(3);
			}
		}
		else /*if(roboMode == 0)*/
		{
			turnGun = getHeading() + e.getBearing() - getRadarHeading();
			//standardMode(e);
			if(e.getDistance() < 50)
			{
				turnGun = e.getBearing() + getHeading() - getRadarHeading();
				setTurnGunRight(turnGun);
				if(getGunHeat() == 0)
				{	
					setFire(3);
				}
			}
			else
			{
				//setTurnGunRight(turnGun);
				if(getGunHeat() == 0)
				{
					double futureX = (enemyCoords[1][0] - enemyCoords[0][0]) / (1 / (Rules.getBulletSpeed(3) * e.getDistance() * 2));
					double futureY = (enemyCoords[1][1] - enemyCoords[0][1]) / (1 / (Rules.getBulletSpeed(3) * e.getDistance() * 2));
					double extraTurn = Math.toDegrees(Math.asin((Math.sqrt((Math.pow(futureX - enemyCoords[1][0], 2) + (Math.pow(futureY - enemyCoords[1][1], 2)))))));
					setTurnGunRight(turnGun + extraTurn);
					setFire(3);
				}
			}
			/*if(getGunHeat() == 0)
			{
				fire(3);
			}*/
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		dir = dir * -1;
	}

	/*
	//The mode for only one robot found
	public void standardMode(ScannedRobotEvent e)
	{

	}

	//The mode for multiple robots found
	public void royaleMode()
	{

	}
	*/

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(15 * dir);
		dir = dir * -1;
	}	
	
	public void onWin(WinEvent e)
	{
		int smileyFace = 0;
		while(smileyFace < 15)
		{
			ahead(6);
			back(6);
			smileyFace++;
		}	
	}
}
