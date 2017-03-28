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

    private int x,y;

    public Coordinate2D update(String shipType, GraphicsContext gc, KeyListen keyListener){
            Coordinate2D newPos = new Coordinate2D(x, y);

            if(shipType.equals("User")){
              newPos = userMoveBehavior(gc, keyListener);
            }
            else if(shipType.equals("Enemy")){
               enemyMoveBehavior(gc, keyListener);
            }

            return newPos;
    }

    public Coordinate2D userMoveBehavior(GraphicsContext gc, KeyListen keyListener){

            Coordinate2D newPosition = new Coordinate2D(x, y);
            if (keyListener.getRightKeyPressed()) {
                newPosition.setX(newPosition.getX() + 5);
            }
            if (keyListener.getLeftKeyPressed()){
                newPosition.setX(newPosition.getX() - 5);
            }
            if (keyListener.getDownKeyPressed()){
                newPosition.setY(newPosition.getY() + 5);
            }
            if (keyListener.getUpKeyPressed()) {
                newPosition.setY(newPosition.getY() - 5);
            }

            return newPosition;

    }

    public void enemyMoveBehavior(GraphicsContext gc, KeyListen keyListener){


    }

    public void setX(int newX){ x = newX; }

    public void setY(int newY){ y = newY; }

    public int getX(){ return x; }

    public int getY(){ return y; }
}
