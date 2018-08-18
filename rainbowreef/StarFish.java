package rainbowreef;

import java.awt.Rectangle;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class StarFish extends GameObject implements Runnable {

    private static int totalScore = 0;
    private static int numOfLives = 3;

    public static int getScore() {
        return totalScore;
    }

    public static int getNumOfLives() {
        return numOfLives;
    }

    public static void subtractNumOfLives() {
        if (numOfLives > 0) {
            numOfLives--;
        }
    }

    public static void addNumOfLives() {
        if (numOfLives < 5) {
            numOfLives++;
        }
    }

    public static void addScore(int score) {
        totalScore += score;
    }

    public static void addStarFish(int x, int y) {
        GameBoard.generateStarFish(x, y);
    }

    private int r;
    private int angle;
    private int vx;
    private int vy;

    public StarFish(int x, int y, int gameLevel) {
        super(x, y);
        this.damage = 5;
        this.angle = 90;
        this.r = 4 + 2 * gameLevel;
        this.width = 20;
        this.height = 20;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void speedUp() {
        r++;
    }

    public void moveForwards() {
        vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        bounds.setLocation(x, y);
        checkBorder();
    }

    private void checkBorder() {
        if (x < 20) {
            x = 20;
        }
        if (x > 600) {
            x = 600;
        }
        if (y < 20) {
            y = 20;
        }
        if (y > 460) {
            isAlive = false;
        }
    }

    @Override
    public void collide(Collidable obj) {
        Rectangle objBounds = obj.getBounds();
        Rectangle intersection = objBounds.intersection(bounds);
        double refX = intersection.getCenterX();
        double refY = intersection.getCenterY();
        if (Math.abs(refY - 440) < 10 && Math.abs(refX - 320) < 300) {
            angle = 270 + (int) Math.ceil(45 * (refX - objBounds.getCenterX()) * 2 / objBounds.getWidth());
        } else if (refY < 20 || (Math.abs(200 - refY) < 10 && Math.abs(320 - refX) < 250)) {
            angle = angle > 180 ? 360 - angle : angle;
        } else if (refX >= 620 || (refX > 100 && refX < 120) || (refX > 220 && refX < 240)
                || (refX > 380 && refX < 400) || (refX > 500 && refX < 520)) {
            if (angle < 90 || angle > 270) {
                angle = angle < 180 ? 180 - angle : 540 - angle;
            }
        } else if (refX <= 20 || (refX > 120 && refX < 140) || (refX > 240 && refX < 260)
                || (refX > 400 && refX < 420) || (refX > 520 && refX < 540)) {
            if (angle > 90 || angle < 270) {
                angle = angle < 180 ? 180 - angle : 540 - angle;
            }
        } else {
            angle = angle > 180 ? 360 - angle : angle;
        }
    }

    @Override
    public void run() {
        while (isAlive) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                System.out.println("StarFish Exception: " + ex.getMessage());
            }
            moveForwards();
        }
    }
}
