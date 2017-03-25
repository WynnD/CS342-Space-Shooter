package Game;

import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.util.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

//TODO: weapons/shooting, main menu(stackpanel/vbox?), moveBehavior, timer(animationTimer?), bound/collision checking,
//TODO: upgrades(in weaponClass?), levels, scoring, save/pause, sound effects
//**priority: enemy movement, timer, main menu, shooting, collision detection

/*
    Timer()
        clearCanvas
        moveShips - this will move enemies, user coordinates updated from listener
        drawShips
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){

        //music to be played during game
        String musicFile = "spaceMusic.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //loops music
        mediaPlayer.play();

        //set background image
        Image background = new Image("http://orig10.deviantart.net/dda0/f/2014/285/2/f/free_for_use_galaxy_background_by_duskydeer-d82jaky.png");
        ImageView imgView = new ImageView(background);
        double width = background.getWidth();
        double height = background.getHeight();

        //create canvas with size of background image
        Canvas canvas = new Canvas(width, height);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        //create stackpane for background image and canvas
        StackPane sp = new StackPane();
        sp.getChildren().addAll(imgView, canvas);

        Scene scene = new Scene(sp);

        primaryStage.setTitle("Galactic Overdrive 3000");
        primaryStage.setScene(scene);
        primaryStage.show();

        ArrayList<Spaceship> ships = new ArrayList<Spaceship>();

        int midScreen = (int) ((width)/2) - 60;

        Image userImage = new Image("http://opengameart.org/sites/default/files/ship_0.png");
        ImageView userImageView = new ImageView(userImage);
        Spaceship userShip = new Spaceship(userImageView, midScreen, 300, 60, 60);

        Image enemyImage = new Image("https://s-media-cache-ak0.pinimg.com/originals/68/0c/d4/680cd456acb325c4918cbe672a839522.png");
        ImageView enemyImageView = new ImageView(enemyImage);
        Spaceship enemyShip = new Spaceship(enemyImageView, midScreen, 20, 60, 60);

        ships.add(userShip);
        ships.add(enemyShip);

        drawShips(graphicsContext, ships);

        //key listeners for arrow keys
        scene.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.RIGHT){
                //just trying out a way to check for collisions, needs fixing
                //userShip.getImageView().getBoundsInParent().intersects(enemyShip.getImageView().getBoundsInParent())

                userShip.setX(userShip.getX() + 5);
            }
            else if(e.getCode() == KeyCode.LEFT) {
                userShip.setX(userShip.getX() - 5);
            }
            else if(e.getCode() == KeyCode.UP){
                userShip.setY(userShip.getY() - 5);
            }
            else if(e.getCode() == KeyCode.DOWN){
                userShip.setY(userShip.getY() + 5);
            }
            else if(e.getCode() == KeyCode.SPACE)
            {
                //shooting sound effect
                String musicFile2 = "shooting.mp3";
                Media sound2 = new Media(new File(musicFile2).toURI().toString());
                MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                mediaPlayer2.play();
            }
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                graphicsContext.clearRect(0, 0, width, height); //clears entire canvas
                drawShips(graphicsContext, ships);
            }
        }.start();
    }


    public static void main(String[] args){
        launch(args);
    }

    public void drawShips(GraphicsContext gc, ArrayList<Spaceship> ships)
    {
        for(Spaceship s: ships)
        {
            s.drawShip(gc);
        }
    }
}
