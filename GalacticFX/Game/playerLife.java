package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Created by noemi_000 on 3/31/2017.
 */
public class playerLife {
    ImageView lifeImageView;
    double x, y;
    double w, h;

    public playerLife(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.w = 20;
        this.h = 20;

        String lifeImageName = "playerLife2_green.png";
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
