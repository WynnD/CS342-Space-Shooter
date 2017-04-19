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
    //private MoveBehavior moveBehavior;
    private ImageView imageView;
    private int width,height;
    private Coordinate2D position;


    public Projectile(Spaceship shipThatIsFiring, Sprite projectileSprite, int verticalSpeed) {
        this.imageView = projectileSprite.getImageView();
        this.shipFiredFrom = shipThatIsFiring;
        width = (int)projectileSprite.getWidth();
        height = (int)projectileSprite.getHeight();
        position = getStartPosition();
        //moveBehavior = new MoveBehavior();
        this.verticalSpeed = verticalSpeed;
        destroyed = false;
    }

    //should this be in shootbehavior?
    public Coordinate2D tryToMove(int index){

        Coordinate2D newPos;

        if(shipFiredFrom.getShipType().equals("User")) {
            newPos = new Coordinate2D(position.getX(), position.getY() - verticalSpeed);
        }
        else
        {
            if(index%2 == 0)
                newPos = new Coordinate2D(position.getX()+1, position.getY() + verticalSpeed);
            else
                newPos = new Coordinate2D(position.getX()-1, position.getY() + verticalSpeed);
        }
        return newPos;
    }

    public void setPosition(Coordinate2D newPosition)
    {
        position = newPosition;
    }


    public ImageView getImageView() {
        return imageView;
    }

    public double getX() {
        return position.getX();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public double getY() {
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

    private double getStartX() {
        double shipX = shipFiredFrom.getX();
        int shipW = (int) shipFiredFrom.getW();
        return shipX+(shipW/2)-(width/2);
    }

    private double getStartY() {
        double shipY = shipFiredFrom.getY();
        if(shipFiredFrom.getShipType().equals("User")) {
            return shipY-height;
        }
        else {
            shipY = (int)(shipY+shipFiredFrom.getH());
            return shipY;
        }
    }

    public void destroy() {
        destroyed = true;
    }

    public boolean destroyed() {
        return destroyed;
    }

    public void display(GraphicsContext gc)
    {
        gc.drawImage(imageView.getImage(), position.getX(), position.getY(), width, height);
    }
}