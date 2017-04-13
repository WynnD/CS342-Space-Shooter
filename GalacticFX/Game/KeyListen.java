package Game;


import javafx.scene.Scene;
import javafx.scene.input.KeyCode;


/**
 * Created by Deon on 3/24/2017.
 */
public class KeyListen {

    private boolean leftKeyPressed = false;
    private boolean rightKeyPressed = false;
    private boolean upKeyPressed = false;
    private boolean downKeyPressed = false;
    private boolean spaceBarPressed = false;
    private Scene scene;

    public KeyListen(Scene scene){

            this.scene = scene;

    }

    public void listen(){
        scene.getRoot().requestFocus();
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                setRightKeyPressed(true);
            } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
                setLeftKeyPressed(true);
            } else if (e.getCode() == KeyCode.UP   || e.getCode() == KeyCode.W) {
                setUpKeyPressed(true);
            } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                setDownKeyPressed(true);
            } else if (e.getCode() == KeyCode.SPACE) {
                setSpaceBarPressed(true);
            }
        });


        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                setRightKeyPressed(false);
            } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
                setLeftKeyPressed(false);
            } else if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
                setUpKeyPressed(false);
            } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                setDownKeyPressed(false);
            } else if (e.getCode() == KeyCode.SPACE) {
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
