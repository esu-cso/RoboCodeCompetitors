package 2023Spring;

import robocode.*;

//Will Youse's Submission, written by ChatGPT

public class AdvBoi extends AdvancedRobot {
  double previousEnergy = 100;
  int movementDirection = 1;
  int gunDirection = 1;

  public void run() {
    while (true) {
      ahead(100 * movementDirection);
      turnGunRight(360 * gunDirection);
    }
  }

  public void onScannedRobot(ScannedRobotEvent e) {
    double absoluteBearing = getHeading() + e.getBearing();
    double bearingFromGun = normalizeBearing(absoluteBearing - getGunHeading());

    if (Math.abs(bearingFromGun) <= 3) {
      setTurnGunRight(bearingFromGun);
      if (getGunHeat() == 0) {
        fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - 0.1));
      }
    } else {
      setTurnGunRight(bearingFromGun);
    }

    if (bearingFromGun == 0) {
      gunDirection *= -1;
    }

    if (e.getEnergy() < previousEnergy) {
      movementDirection *= -1;
    }
    previousEnergy = e.getEnergy();
  }

  double normalizeBearing(double angle) {
    while (angle >  180) angle -= 360;
    while (angle < -180) angle += 360;
    return angle;
  }
}