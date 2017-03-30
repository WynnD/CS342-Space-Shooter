package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Wynn on 3/26/2017.
 */
public class Projectile {
    private Spaceship shipFiredFrom;
    private boolean destroyed;
    private int damageOnHit;
    private int verticalSpeed;
    //    private MoveBehavior moveBehavior;
    private ImageView imageView;
    private int width,height;
    private Coordinate2D position;


    public Projectile(Spaceship shipThatIsFiring, ImageView imageView) {
        this.imageView = imageView;
        this.shipFiredFrom = shipThatIsFiring;
        width = (int) imageView.boundsInParentProperty().getValue().getWidth();   //DEON: get width and height this way instead of a parameter
        height = (int) imageView.boundsInParentProperty().getValue().getHeight();
        position = getStartPosition();
//        moveBehavior = new MoveBehavior();
        verticalSpeed = 15;
        destroyed = false;
    }


    public void tryToMove(){
        position.translateY(-verticalSpeed);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getX() {
        return position.getX();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setY(y);
    }

    public void translateX(int offset) {
        position.translateX(offset);
    }

    public void translateY(int offset) {
        position.translateY(offset);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private Coordinate2D getStartPosition() {
        return new Coordinate2D(getStartX(), getStartY());
    }

    private int getStartX() {
        int shipX = shipFiredFrom.getX();
        int shipW = (int) shipFiredFrom.getW();
        return shipX+(shipW/2)-(width/2);
    }

    private int getStartY() {
        int shipY = shipFiredFrom.getY();
        return shipY-height;
    }

    public void destroy() {
        destroyed = true;
    }

    public boolean destroyed() {
        return destroyed;
    }
}