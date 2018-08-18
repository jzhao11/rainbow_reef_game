package rainbowreef;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class ExtraLife extends PowerUp {

    public ExtraLife(int x, int y) {
        super(x, y);
        this.blockName = "ExtraLife";
    }
    
    @Override
    public void collide(Collidable obj) {
        super.collide(obj);
        StarFish.addNumOfLives();
    }
}
