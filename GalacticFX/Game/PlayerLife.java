package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Created by noemi_000 on 3/31/2017.
 */
public class PlayerLife {
    private ImageView lifeImageView;
    private double x, y;
    private double w, h;
    //sprite instead of w/h/imageview

    public PlayerLife(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.w = 22;
        this.h = 18;

        Sprite lifeSprite = new Sprite("Images/playerLife3_green.png", w, h);
        this.lifeImageView = lifeSprite.getImageView();
    }

    public void drawLife(GraphicsContext gc){
        gc.drawImage(lifeImageView.getImage(), x, y, w, h);
    }
}
