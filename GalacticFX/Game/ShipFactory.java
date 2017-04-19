package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Created by Deon on 3/23/2017.
 */
public class ShipFactory {

    private KeyListen keyListener;

    public ShipFactory(KeyListen keyListener){
        this.keyListener = keyListener;
    }

    public Spaceship makeShip(String shipType, int x, int y) {

        Coordinate2D initialPosition = new Coordinate2D(x, y);

        if (shipType.equals("User")) {
            Sprite userSprite = new Sprite("Images/playerShip3_green.png", 45, 35);
            Spaceship userShip = new UserSpaceship(shipType, userSprite, keyListener, initialPosition);
            return userShip;
        } else if (shipType.equals("Enemy1")) {
            Sprite enemySprite = new Sprite("Images/enemyRed1.png", 40, 40);
            Spaceship enemyShip = new EnemySpaceship1(shipType, enemySprite, initialPosition);
            return enemyShip;
        } else if (shipType.equals("Enemy2")) {
            Sprite enemySprite = new Sprite("Images/enemyRed1.png", 40, 40);
            Spaceship enemyShip = new EnemySpaceship2(shipType, enemySprite, initialPosition);
            return enemyShip;

            //else if() for other shipTypes "Lvl 1 Enemy", "Lvl 2 Enemy", etc.
        } else {
            return null;
        }
    }
}
