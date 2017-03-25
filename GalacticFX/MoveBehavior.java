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

    public void update(String shipType, GraphicsContext gc, KeyListen keyListener){
            if(shipType.equals("User")){
                userMoveBehavior(gc, keyListener);
            }

    }

    public void userMoveBehavior(GraphicsContext gc, KeyListen keyListener){

            if (keyListener.getRightKeyPressed()) {
                x+=5;
            }
            if (keyListener.getLeftKeyPressed()){
                x-=5;
            }
            if (keyListener.getDownKeyPressed()){
                y+=5;
            }
            if (keyListener.getUpKeyPressed()) {
                y-=5;
            }

    }

    public void setX(int newX){ x = newX; }

    public void setY(int newY){ y = newY; }

    public int getX(){ return x; }

    public int getY(){ return y; }
}
