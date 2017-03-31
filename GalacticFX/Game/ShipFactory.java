package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Created by Deon on 3/23/2017.
 */
public class ShipFactory {

    private GraphicsContext gc;
    private KeyListen keyListener;

    public ShipFactory(GraphicsContext gc, KeyListen keyListener){
        this.gc = gc;
        this.keyListener = keyListener;
    }

    public Spaceship makeShip(String shipType) {

        if (shipType.equals("User")) {
            Sprite userSprite = new Sprite("playerShip3_green.png", 45, 35);
            Spaceship userShip = new Spaceship(userSprite.getImageView(), shipType, gc, keyListener);
            return userShip;
        } else if (shipType.equals("Enemy")) {
            Sprite enemySprite = new Sprite("enemyRed1.png", 40, 40);
            Spaceship enemyShip = new Spaceship(enemySprite.getImageView(), shipType, gc, keyListener);
            return enemyShip;
        }

        //else if() for other shipTypes "Lvl 1 Enemy", "Lvl 2 Enemy", etc.

        else return null;
    }
}
