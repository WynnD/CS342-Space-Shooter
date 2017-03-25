package Game;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
/**
 * Created by Deon on 3/24/2017.
 */
public class KeyListen {

    private boolean leftKeyPressed = false;
    private boolean rightKeyPressed = false;
    private boolean upKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean spaceBarPressed = false;

    public KeyListen(Scene scene){

        scene.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.RIGHT){
                setRightKeyPressed(true);
            }
            else if(e.getCode() == KeyCode.LEFT) {
                setLeftKeyPressed(true);
            }
            else if(e.getCode() == KeyCode.UP){
                setUpKeyPressed(true);
            }
            else if(e.getCode() == KeyCode.DOWN){
                setDownKeyPressed(true);
            }
            else if(e.getCode() == KeyCode.SPACE) {
                setSpaceBarPressed(true);
            }
        });



        scene.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.RIGHT){
                setRightKeyPressed(false);
            }
            else if(e.getCode() == KeyCode.LEFT) {
                setLeftKeyPressed(false);
            }
            else if(e.getCode() == KeyCode.UP){
                setUpKeyPressed(false);
            }
            else if(e.getCode() == KeyCode.DOWN){
                setDownKeyPressed(false);
            }
            else if(e.getCode() == KeyCode.SPACE) {
                setSpaceBarPressed(false);
            }
        });
    }

    public void setLeftKeyPressed(boolean value){ leftKeyPressed = value; }

    public void setRightKeyPressed(boolean value){ rightKeyPressed = value; }

    public void setUpKeyPressed(boolean value){ upKeyPressed = value; }

    public void setDownKeyPressed(boolean value){ downKeyPressed = value; }

    public void setSpaceBarPressed(boolean value){ spaceBarPressed = value; }


    public boolean getLeftKeyPressed(){ return leftKeyPressed; }

    public boolean getRightKeyPressed(){ return rightKeyPressed; }

    public boolean getUpKeyPressed(){ return upKeyPressed; }

    public boolean getDownKeyPressed(){ return downKeyPressed; }

    public boolean getSpaceBarPressed(){ return spaceBarPressed; }



}
