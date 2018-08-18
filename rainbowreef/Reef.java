package rainbowreef;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class Reef extends Block {
    
    private int score;
    
    public Reef(int x, int y, int level) {
        super(x, y);
        this.score = 10 * level;
        this.blockName = "Reef" + level;
        this.isBreakable = true;
    }
    
    @Override
    public void collide(Collidable obj) {
        super.collide(obj);
        this.sound.play(false);
        ((StarFish) obj).addScore(score);
    }
}
