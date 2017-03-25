package Game;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

/**
 * Created by Deon on 3/23/2017.
 */
public class ShipFactory {
    public Spaceship makeShip(String shipType, GraphicsContext gc, KeyListen keyListener){

        if(shipType.equals("User")){
            Image userImage = new Image("http://opengameart.org/sites/default/files/ship_0.png");
            ImageView userImageView = new ImageView(userImage);
            Spaceship userShip = new Spaceship(userImageView, shipType);
            return userShip;
        }

        //else if() for other shipTypes "Lvl 1 Enemy", "Lvl 2 Enemy", etc.

        else return null;
    }
}
