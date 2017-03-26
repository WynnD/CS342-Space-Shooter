package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Deon on 3/23/2017.
 */
public class ShootBehavior {

    boolean alreadyShot = false;

    public void update(Coordinate2D ship_coords, KeyListen keyListener, ArrayList<Projectile> projectiles){
        if(keyListener.getSpaceBarPressed()) {
            if(alreadyShot == false) {
                String musicFile2 = "shooting.mp3";
                String projectileImageName = "projectile.png";
                Media sound2 = new Media(new File(musicFile2).toURI().toString());
                MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                mediaPlayer2.play();


                //
                Image projectileImage = new Image(new File(projectileImageName).toURI().toString());
                ImageView projectileImageView = new ImageView(projectileImage);
                projectiles.add(new Projectile(ship_coords, projectileImageView));

                alreadyShot = true;
            }
        }
        else{
            alreadyShot = false;
        }
    }

}
