package Game;

import javafx.application.Application;
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

//ISSUE: panel is resized but image/canvas are not

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
        //Image background = new Image("http://1-background.com/images/stars-1/starry-night-purple-tile.jpg");
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


        //Image spImage = new Image("https://3.bp.blogspot.com/-jGC08Dy0zg8/U405cNq1-MI/AAAAAAAABqU/38d5rmV1S8Y/s1600/redfighter0006.png");
        Image spImage = new Image("http://opengameart.org/sites/default/files/ship_0.png");
        Spaceship userShip = new Spaceship(spImage, midScreen, 300, 60, 60);

        //Image spImage2 = new Image("http://3.bp.blogspot.com/-mKR21lEuHoc/Uc850TNIW3I/AAAAAAAAAr4/8mzOxikZ7EE/s302/aliensprite2.png");
        Image spImage2 = new Image("https://s-media-cache-ak0.pinimg.com/originals/68/0c/d4/680cd456acb325c4918cbe672a839522.png");
        Spaceship enemyShip = new Spaceship(spImage2, midScreen, 20, 60, 60);

        ships.add(userShip);
        ships.add(enemyShip);

        drawShips(graphicsContext, ships);

        //key listeners for arrow keys
        //currently can't press space and arrow at same time
        scene.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.RIGHT){
                graphicsContext.clearRect(0, 0, width, height); //clears entire canvas
                userShip.setX(userShip.getX() + 5);
                drawShips(graphicsContext, ships);
            }
            else if(e.getCode() == KeyCode.LEFT) {
                graphicsContext.clearRect(0, 0, width, height); //clears entire canvas
                userShip.setX(userShip.getX() - 5);
                drawShips(graphicsContext, ships);
            }
            else if(e.getCode() == KeyCode.UP){
                graphicsContext.clearRect(0, 0, width, height); //clears entire canvas
                userShip.setY(userShip.getY() - 5);
                drawShips(graphicsContext, ships);
            }
            else if(e.getCode() == KeyCode.DOWN){
                graphicsContext.clearRect(0, 0, width, height); //clears entire canvas
                userShip.setY(userShip.getY() + 5);
                drawShips(graphicsContext, ships);
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

