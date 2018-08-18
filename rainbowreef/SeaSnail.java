package rainbowreef;

import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class SeaSnail extends GameObject implements Observer {

    private int r = 6;
    private boolean isRightPressed;
    private boolean isLeftPressed;

    public SeaSnail(int x, int y) {
        super(x, y);
        this.damage = 0;
        this.width = 80;
        this.height = 30;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void toggleRightPressed() {
        isRightPressed = true;
    }

    public void toggleLeftPressed() {
        isLeftPressed = true;
    }

    public void unToggleRightPressed() {
        isRightPressed = false;
    }

    public void unToggleLeftPressed() {
        isLeftPressed = false;
    }

    private void moveLeft() {
        x -= r;
        bounds.setLocation(x, y);
        checkBorder();
    }

    private void moveRight() {
        x += r;
        bounds.setLocation(x, y);
        checkBorder();
    }

    private void checkBorder() {
        if (x < 20) {
            x = 20;
        }
        if (x > 540) {
            x = 540;
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        if (isLeftPressed) {
            moveLeft();
        }
        if (isRightPressed) {
            moveRight();
        }
    }
}
