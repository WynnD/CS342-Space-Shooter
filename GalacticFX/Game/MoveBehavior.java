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
    private boolean moveRight;
    private boolean moveLeft = true;
    private int position = 0;

    public MoveBehavior (Spaceship ship) {
        this.ship = ship;
    }

    public Coordinate2D update(KeyListen keyListener){
        Coordinate2D newPos = new Coordinate2D(ship.getX(), ship.getY());

        if(ship.getShipType().equals("User")){
            newPos = userMoveBehavior(keyListener);
        }
        else if(ship.getShipType().equals("Enemy")){
            newPos = enemyMoveBehavior(keyListener);
        }

        return newPos;
    }

    public Coordinate2D userMoveBehavior(KeyListen keyListener){
        Coordinate2D newPosition = new Coordinate2D(ship.getX(), ship.getY());
        if (keyListener.getRightKeyPressed()) {
            newPosition.translateX(5);
        }
        if (keyListener.getLeftKeyPressed()){
            newPosition.translateX(-5);
        }
        if (keyListener.getDownKeyPressed()){
            newPosition.translateY(5);
        }
        if (keyListener.getUpKeyPressed()) {
            newPosition.translateY(-5);
        }

        return newPosition;

    }

    public Coordinate2D enemyMoveBehavior(KeyListen keyListener) {
        Coordinate2D newPosition = new Coordinate2D(ship.getX(), ship.getY());
        if(moveLeft){
            //System.out.println("moveleft");
            if(position > -100) {
                position -= 2;
                newPosition.translateX(-2);
            }
            else{
                moveRight = true;
                moveLeft = false;
            }
        }
        else if(moveRight){
            //System.out.println("moveright");
            if(position < 100){
                position+=2;
                newPosition.translateX(2);
            }

            else{
                moveLeft = true;
                moveRight = false;
            }

        }

        return newPosition;
    }

}
