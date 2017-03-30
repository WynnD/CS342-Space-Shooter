package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Deon on 3/23/2017.
 */
public class ShootBehavior {

    private boolean alreadyShot = false;
    private Spaceship shipThatIsFiring;
    private KeyListen keyListener;
    private ArrayList<Projectile> projectiles;

    public ShootBehavior(Spaceship shipThatIsFiring, KeyListen keyListener, ArrayList<Projectile> projectiles) {
        this.shipThatIsFiring = shipThatIsFiring;
        this.keyListener = keyListener;
        this.projectiles = projectiles;
    }

    public void update(){
        if(keyListener.getSpaceBarPressed()) {
            if(alreadyShot == false && shipThatIsFiring.getShipType().equals("User")) {
                String musicFile2 = "shooting.mp3";
                String projectileImageName = "projectile_small.png";
                Media sound2 = new Media(new File(musicFile2).toURI().toString());
                MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                mediaPlayer2.play();


                //
                Image projectileImage = new Image(new File(projectileImageName).toURI().toString());
                ImageView projectileImageView = new ImageView(projectileImage);
                projectileImageView.setFitHeight(20);
                projectileImageView.setFitWidth(20);
                projectiles.add(new Projectile(shipThatIsFiring, projectileImageView));
                alreadyShot = true;
            }
        }
        else{
            alreadyShot = false;
        }
    }

}
