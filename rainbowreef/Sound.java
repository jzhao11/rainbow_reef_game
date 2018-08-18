package rainbowreef;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 * @author Jianfei Zhao
 * @email jzhao11@mail.sfsu.edu
 */
public class Sound {

    private AudioClip player;

    public Sound(String fileName) {
        player = Applet.newAudioClip(Sound.class.getResource("Resources/" + fileName));
    }

    public void play(boolean isLoopPlayBack) {
        if (isLoopPlayBack) {
            player.loop();
        } else {
            player.play();
        }
    }
}
