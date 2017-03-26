package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Wynn on 3/26/2017.
 */
public class Projectile {
    private int damageOnHit;
    private int verticalSpeed;
//    private MoveBehavior moveBehavior;
    private GraphicsContext gc;
    private ImageView imageView;
    private double width,height;
    private int x,y;


    public Projectile(Coordinate2D position, ImageView imageView) {
        this.imageView = imageView;
        width = imageView.boundsInParentProperty().getValue().getWidth();   //DEON: get width and height this way instead of a parameter
        height = imageView.boundsInParentProperty().getValue().getHeight();

//        moveBehavior = new MoveBehavior();
        verticalSpeed = 15;
    }


    public void tryToMove(){
        y -= verticalSpeed;
    }

    public ImageView getImageView() {
        return imageView;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}