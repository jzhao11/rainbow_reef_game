package rainbowreef;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class GameObject implements Collidable {

    protected static HashMap<String, BufferedImage> imgSrcs = new HashMap<>(); // store image resources

    static {
        try {
            imgSrcs.put("Background1", ImageIO.read(GameObject.class.getResource("Resources/Background1.bmp")));
            imgSrcs.put("Background2", ImageIO.read(GameObject.class.getResource("Resources/Background2.bmp")));
            imgSrcs.put("StarFish", ImageIO.read(GameObject.class.getResource("Resources/StarFish.gif")));
            imgSrcs.put("SeaSnail", ImageIO.read(GameObject.class.getResource("Resources/SeaSnail.gif")));
            imgSrcs.put("Block", ImageIO.read(GameObject.class.getResource("Resources/Block.gif")));
            imgSrcs.put("Reef1", ImageIO.read(GameObject.class.getResource("Resources/Reef1.gif")));
            imgSrcs.put("Reef2", ImageIO.read(GameObject.class.getResource("Resources/Reef2.gif")));
            imgSrcs.put("Reef3", ImageIO.read(GameObject.class.getResource("Resources/Reef3.gif")));
            imgSrcs.put("Reef4", ImageIO.read(GameObject.class.getResource("Resources/Reef4.gif")));
            imgSrcs.put("Reef5", ImageIO.read(GameObject.class.getResource("Resources/Reef5.gif")));
            imgSrcs.put("Reef6", ImageIO.read(GameObject.class.getResource("Resources/Reef6.gif")));
            imgSrcs.put("Reef7", ImageIO.read(GameObject.class.getResource("Resources/Reef7.gif")));
            imgSrcs.put("Wall", ImageIO.read(GameObject.class.getResource("Resources/Wall.gif")));
            imgSrcs.put("Octopus", ImageIO.read(GameObject.class.getResource("Resources/Octopus.gif")));
            imgSrcs.put("ExtraStarFish", ImageIO.read(GameObject.class.getResource("Resources/ExtraStarFish.gif")));
            imgSrcs.put("ExtraScore", ImageIO.read(GameObject.class.getResource("Resources/ExtraScore.gif")));
            imgSrcs.put("ExtraLife", ImageIO.read(GameObject.class.getResource("Resources/ExtraLife.gif")));
            imgSrcs.put("Life", ImageIO.read(GameObject.class.getResource("Resources/Life.png")));
            imgSrcs.put("Congratulation", ImageIO.read(GameObject.class.getResource("Resources/Congratulation.bmp")));
        } catch (Exception ex) {
            System.out.println("GameObject Resource Not Found: " + ex.getMessage());
        }
    }

    public static BufferedImage getImg(String gameObjectName) {
        return imgSrcs.get(gameObjectName);
    }

    protected int width = 40;
    protected int height = 20;
    protected int x;
    protected int y;
    protected boolean isAlive;
    protected boolean isBreakable;
    protected Rectangle bounds;
    protected int damage;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        this.isAlive = true;
        this.isBreakable = false;
        this.bounds = new Rectangle(x, y, width, height);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isBreakable() {
        return isBreakable;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void collide(Collidable obj) {
    }
}
