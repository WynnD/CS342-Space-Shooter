package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Created by noemi_000 on 3/31/2017.
 */
public class PlayerLife {
    ImageView lifeImageView;
    double x, y;
    double w, h;

    public PlayerLife(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.w = 22;
        this.h = 18;

        String lifeImageName = "Images/playerLife3_green.png";
        Image lifeImage = new Image(new File(lifeImageName).toURI().toString());
        ImageView newLifeImageView = new ImageView(lifeImage);
        newLifeImageView.setFitHeight(h);
        newLifeImageView.setFitWidth(w);
        this.lifeImageView = newLifeImageView;
    }

    public void drawLife(GraphicsContext gc){
        gc.drawImage(lifeImageView.getImage(), x, y, w, h);
    }
}
