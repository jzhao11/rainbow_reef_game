package rainbowreef;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class Block extends GameObject {

    protected String blockName;
    protected int health;
    protected Sound sound;

    public Block(int x, int y) {
        super(x, y);
        this.health = 5;
        this.damage = 0;
        this.blockName = "Block";
        this.sound = new Sound("HitBlock.wav");
    }

    public String getBlockName() {
        return blockName;
    }

    @Override
    public void collide(Collidable obj) {
        if (isBreakable) {
            health = (health > obj.getDamage()) ? health - obj.getDamage() : 0;
            if (health == 0) {
                isAlive = false;
            }
        }
        this.sound.play(false);
    }
}
