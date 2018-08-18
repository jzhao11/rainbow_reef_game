package rainbowreef;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class ExtraScore extends PowerUp {

    public ExtraScore(int x, int y) {
        super(x, y);
        this.blockName = "ExtraScore";
    }

    @Override
    public void collide(Collidable obj) {
        super.collide(obj);
        StarFish.addScore(500);
    }
}
