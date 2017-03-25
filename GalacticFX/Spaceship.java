package Game;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import java.util.Stack;


public class Spaceship {

    public MoveBehavior moveBehavior;
    private ShootBehavior shootBehavior;
    private String shipType;
    private ImageView shipImage;
    //private int x, y;
    private double w, h;    //DEON: width and height should be doubles
    //private boolean isAlive; //this can be changed when collision occurs then spaceship can be deleted from list

    public Spaceship(ImageView imageView, String shipType)
    {
        shipImage = imageView;
        this.shipType = shipType;

        w = imageView.boundsInParentProperty().getValue().getWidth();   //DEON: get width and height this way instead of a parameter
        h = imageView.boundsInParentProperty().getValue().getHeight();

        moveBehavior = new MoveBehavior();
        shootBehavior = new ShootBehavior();

    }

    public void tryToMove(GraphicsContext gc,KeyListen keyListener){
        moveBehavior.update(shipType, gc, keyListener);
    }

    public void tryToShoot(GraphicsContext gc, KeyListen keyListener) {
        shootBehavior.update(gc, keyListener);
    }

    public void drawShip(GraphicsContext gc) {
        gc.drawImage(shipImage.getImage(), this.getX(), this.getY(), w, h);
    }

    public ImageView getImageView() {
        return shipImage;
    }

    public int getX() { return moveBehavior.getX(); }

    public int getY() { return moveBehavior.getY(); }

    public void setX(int x) { moveBehavior.setX(x); }

    public void setY(int y) { moveBehavior.setY(y); }

    public String getShipType(){ return shipType;}
}
