package Game;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;


public class Spaceship {

    private ImageView shipImage;
    private int x, y, w, h;
    //private boolean isAlive; //this can be changed when collision occurs then spaceship can be deleted from list

    public Spaceship(ImageView imageView, int x, int y, int w, int h)
    {
        this.shipImage = imageView;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void drawShip(GraphicsContext gc)
    {
        gc.drawImage(shipImage.getImage(), x, y, w, h);
    }

    public ImageView getImageView() {
        return shipImage;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
