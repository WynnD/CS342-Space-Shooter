package Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Deon on 3/23/2017.
 */
public interface ShootBehavior {
    void update();
}

class ShootOneBullet implements ShootBehavior {

    private boolean alreadyShot = false;
    private Spaceship shipThatIsFiring;
    private KeyListen keyListener;
    private ArrayList<Projectile> projectiles;

    public ShootOneBullet(Spaceship shipThatIsFiring, KeyListen keyListener, ArrayList<Projectile> projectiles) {
        this.shipThatIsFiring = shipThatIsFiring;
        this.keyListener = keyListener;
        this.projectiles = projectiles;
    }

    public void update(){
        if(keyListener.getSpaceBarPressed()) {
            //create new projectile
            if(!alreadyShot && shipThatIsFiring.getShipType().equals("User")) {
                String musicFile2 = "Music/shooting.mp3";
                Media sound2 = new Media(new File(musicFile2).toURI().toString());
                MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                mediaPlayer2.play();

                Sprite projectileSprite = new Sprite("Images/laserGreen04.png", 8, 15);
                projectiles.add(new Projectile(shipThatIsFiring, projectileSprite, 8));
                alreadyShot = true;
            }
        }
        else{
            alreadyShot = false;
        }
    }

}
