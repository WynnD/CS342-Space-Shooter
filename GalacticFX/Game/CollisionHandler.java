package Game;

import java.util.*;
import javafx.scene.image.ImageView;
import javafx.geometry.BoundingBox;

public class CollisionHandler {

    ArrayList<Spaceship> ships;


    public CollisionHandler(ArrayList<Spaceship> ships) {
        this.ships = ships;
    }

    /*public boolean detectBorderCollision(int x, int y) {
        if ((x >= w || x <= 0) || (y <= 0 || y >= h))
            return true;
        else
            return false;
    }*/

    /*public boolean detectShipCollision(ImageView shipImage) {
        for (Spaceship s : ships) {
            if(s.getImageView())
        }

    }*/
}