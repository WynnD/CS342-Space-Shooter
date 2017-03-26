package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
            Image userImage = new Image("http://opengameart.org/sites/default/files/ship_0.png");
            ImageView userImageView = new ImageView(userImage);
            Spaceship userShip = new Spaceship(userImageView, shipType, gc, keyListener);
            return userShip;
        } else if (shipType.equals("Enemy")) {
            Image enemyImage = new Image("https://s-media-cache-ak0.pinimg.com/originals/68/0c/d4/680cd456acb325c4918cbe672a839522.png");
            ImageView enemyImageView = new ImageView(enemyImage);
            enemyImageView.setFitHeight(60);
            enemyImageView.setFitWidth(60);

            Spaceship enemyShip = new Spaceship(enemyImageView, shipType, gc, keyListener);
            return enemyShip;

        }

        //else if() for other shipTypes "Lvl 1 Enemy", "Lvl 2 Enemy", etc.

        else return null;
    }
}
