package rainbowreef;

import java.awt.Rectangle;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class Octopus extends Block {
    
    public Octopus(int x, int y) {
        super(x, y);
        this.health = 10;
        this.height = 40;
        this.blockName = "Octopus";
        this.isBreakable = true;
        this.bounds = new Rectangle(x, y, width, height);
        this.sound = new Sound("HitOctopus.wav");
    }
    
    @Override
    public void collide(Collidable obj) {
        super.collide(obj);
        ((StarFish) obj).speedUp();
        ((StarFish) obj).addScore(100);
    }
}
