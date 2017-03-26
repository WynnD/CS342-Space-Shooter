package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import java.util.ArrayList;


public class Spaceship {

    private MoveBehavior moveBehavior;
    private ShootBehavior shootBehavior;
    private GraphicsContext gc;
    private KeyListen keyListener;
    private String shipType;
    private ImageView shipImage;
    private Coordinate2D position;
    private double w, h;
    private ArrayList<Projectile> projectiles;

    public Spaceship(ImageView imageView, String shipType, GraphicsContext graphicsContext, KeyListen keyListen)
    {
        shipImage = imageView;
        this.shipType = shipType;

        gc = graphicsContext;
        keyListener = keyListen;

        w = imageView.boundsInParentProperty().getValue().getWidth();   //DEON: get width and height this way instead of a parameter
        h = imageView.boundsInParentProperty().getValue().getHeight();

        moveBehavior = new MoveBehavior();
        shootBehavior = new ShootBehavior();
        projectiles = new ArrayList<>();
    }

    public void tryToMove(){

        moveBehavior.update(shipType, gc, keyListener);
    }

    public void tryToShoot() {
        shootBehavior.update(position, keyListener, projectiles);
    }

    public void drawShip() {
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
