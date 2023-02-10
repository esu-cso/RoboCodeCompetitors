package 2023Spring;
import robocode.*;
import java.util.Random;
import java.awt.Color;

/**
 * BoxBox2 - a robot by Yusuf Abbas
 */
public class BoxBox2 extends Robot
{
	private boolean isAlGay = true;
	public Random rn = new Random();
	public void run() {

		while(true) {
			ahead(100);
			turnRight(90);
			turnGunRight(90);
			fire(1);
			ahead(100);
			turnRight(90);
			turnGunRight(90);
			fire(1);
			ahead(100);
			turnRight(90);
			turnGunRight(90);
			fire(1);
			ahead(100);
			fire(1);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(1);
		fire(1);
		fire(1);
	}


	public void onHitByBullet(HitByBulletEvent e) {

		setColors(Color.black,Color.black,Color.black);	
		back(50);
		fire(1);
		fire(3);
	}

	public void onBulletHit(BulletHitEvent event){
		setColors(Color.white,Color.white,Color.white);	
		
		back(25);
		ahead(25);
		back(25);
		ahead(25);
		back(25);
		ahead(50);
		//COD Teabagging effect but robot
	}

	public void onHitWall(HitWallEvent e) {
		int decider = rn.nextInt(4);
			if(decider <2)
			{
				turnLeft(180);
				ahead(200);
			}
			else{
				turnRight(180);
				ahead(200);
			}
		}	
}
