package Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Created by noemi_000 on 3/31/2017.
 */


public class Sprite {

    private ImageView imageView;

    private double width, height;

    public Sprite(String imgName, double width, double height){

        Image newImage = new Image(new File(imgName).toURI().toString());
        ImageView newImageView = new ImageView(newImage);
        newImageView.setFitHeight(height);
        newImageView.setFitWidth(width);
        this.width = width;
        this.height = height;
        this.imageView = newImageView;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public ImageView getImageView() {
        return imageView;
    }

}
