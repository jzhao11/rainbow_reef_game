package rainbowreef;

import java.awt.Rectangle;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class Wall extends Block {

    public Wall(int x, int y) {
        super(x, y);
        this.width = 20;
        this.blockName = "Wall";
        this.bounds = new Rectangle(x, y, width, height);
    }
}
