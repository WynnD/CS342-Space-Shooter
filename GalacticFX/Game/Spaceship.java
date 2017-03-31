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

    private Boolean canMove;

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public Spaceship(ImageView imageView, String shipType, GraphicsContext graphicsContext, KeyListen keyListen)
    {
        shipImage = imageView;
        this.shipType = shipType;

        gc = graphicsContext;
        keyListener = keyListen;

        w = imageView.boundsInParentProperty().getValue().getWidth();   //DEON: get width and height this way instead of a parameter
        h = imageView.boundsInParentProperty().getValue().getHeight();

        projectiles = new ArrayList<>();
        moveBehavior = new MoveBehavior(this);
        shootBehavior = new ShootBehavior(this, keyListener, projectiles);
        this.position = new Coordinate2D(0,0);
        this.canMove = true;
    }


    public Coordinate2D tryToMove(){

        Coordinate2D newPosition = moveBehavior.update(keyListener);
        return newPosition;
    }

    public void tryToShoot() {
        shootBehavior.update();
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
        position.translateX(offset);
    }

    public void translateY(int offset) {
        position.translateY(offset);
    }

    public String getShipType(){ return shipType; }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }
}