package Game;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;


public class Spaceship {

    private Image spImage;
    private int x, y, w, h;
    //private boolean isAlive; //this can be changed when collision occurs then it can be deleted from list

    public Spaceship(Image image, int x, int y, int w, int h)
    {
        this.spImage = image;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void drawShip(GraphicsContext gc)
    {
        gc.drawImage(spImage, x, y, w, h);
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
