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

        moveBehavior = new MoveBehavior(this);
        shootBehavior = new ShootBehavior();
        projectiles = new ArrayList<>();
        this.position = new Coordinate2D(0,0);
    }

    public void tryToMove(){
        moveBehavior.update(keyListener);
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

    public int getX() { return position.getX(); }

    public int getY() { return position.getY(); }

    public void setX(int x) { position.setX(x); }

    public void setY(int y) { position.setY(y); }

    public void translateX(int offset) {
        position.setX(position.getX()+offset);
    }

    public void translateY(int offset) {
        position.setY(position.getY()+offset);
    }


    public String getShipType(){ return shipType; }
}
