package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by Deon on 3/23/2017.
 */
public class ShootBehavior {

    boolean alreadyShot = false;

    public void update(GraphicsContext gc, KeyListen keyListener){
        if(keyListener.getSpaceBarPressed()) {
            if(alreadyShot == false) {
                String musicFile2 = "shooting.mp3";
                Media sound2 = new Media(new File(musicFile2).toURI().toString());
                MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                mediaPlayer2.play();
                alreadyShot = true;
            }
        }
        else{
            alreadyShot = false;
        }
    }
}
