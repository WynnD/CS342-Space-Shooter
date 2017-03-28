package Game;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

/**
 * Created by Deon on 3/23/2017.
 */
public class MoveBehavior {

    private Spaceship ship;

    public MoveBehavior (Spaceship ship) {
        this.ship = ship;
    }

    public void update(KeyListen keyListener){
            if(ship.getShipType().equals("User")){
                userMoveBehavior(keyListener);
            }
            else if(ship.getShipType().equals("Enemy")){
                enemyMoveBehavior(keyListener);
            }
    }

    public void userMoveBehavior(KeyListen keyListener){

            if (keyListener.getRightKeyPressed()) {
                ship.translateX(5);
            }
            if (keyListener.getLeftKeyPressed()){
                ship.translateX(-5);
            }
            if (keyListener.getDownKeyPressed()){
                ship.translateY(5);
            }
            if (keyListener.getUpKeyPressed()) {
                ship.translateY(-5);
            }

    }

    public void enemyMoveBehavior(KeyListen keyListener){

    }
}
