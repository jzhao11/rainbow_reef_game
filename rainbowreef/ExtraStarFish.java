package rainbowreef;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class ExtraStarFish extends PowerUp {

    public ExtraStarFish(int x, int y) {
        super(x, y);
        this.blockName = "ExtraStarFish";
    }

    @Override
    public void collide(Collidable obj) {
        super.collide(obj);
        StarFish.addStarFish(obj.getX(), obj.getY());
    }
}
