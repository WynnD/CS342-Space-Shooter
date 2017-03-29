package Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.geometry.BoundingBox;

import java.io.File;
import java.util.*;

//TODO: weapons/shooting, main menu(stackpanel/vbox?), moveBehavior, timer(animationTimer?), bound/collision checking,
//TODO: upgrades(in weaponClass?), levels, scoring, save/pause, sound effects
//**priority: enemy movement, timer, main menu, shooting, collision detection

//ISSUE: panel is resized but image/canvas are not

public class Main extends Application {

    private GraphicsContext graphicsContext;

    @Override
    public void start(Stage primaryStage) {

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
        graphicsContext = canvas.getGraphicsContext2D();
        //create stackpane for background image and canvas
        StackPane sp = new StackPane();
        sp.getChildren().addAll(imgView, canvas);

        Scene scene = new Scene(sp);

        primaryStage.setTitle("Galactic Overdrive 3000");
        primaryStage.setScene(scene);


        KeyListen keyListener = new KeyListen(scene);

        ArrayList<Spaceship> ships = new ArrayList<Spaceship>();
        ArrayList<Projectile> projectiles = new ArrayList<>();

        int midScreen = (int) ((width) / 2);

        ShipFactory factory = new ShipFactory(graphicsContext, keyListener);

        Spaceship userShip = factory.makeShip("User");
        userShip.setX(midScreen);
        userShip.setY(300);

        Spaceship enemyShip = factory.makeShip("Enemy");
        enemyShip.setX(midScreen);
        enemyShip.setY(100);


        ships.add(userShip);
        ships.add(enemyShip);

        BoundingBox window = new BoundingBox(0, 0, width, height);
        CollisionHandler ch = new CollisionHandler(ships);


        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                keyListener.listen();
                updateShips(ships, ch, window);    //Move ships and/or have the ships shoot
                graphicsContext.clearRect(0, 0, width, height);  //Wipe Screen of all ships
                drawShips(ships);                   //Draw updated ships
                drawProjectiles(projectiles);
            }
        }.start();


        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public void updateShips(ArrayList<Spaceship> ships, CollisionHandler ch, BoundingBox window){
        BoundingBox windowWithShipAdjustment;
        for(Spaceship s: ships)
        {

            Coordinate2D newPosition = s.tryToMove();

            // get bounding box with adjustment for ship size
            windowWithShipAdjustment = new BoundingBox(0,0,window.getMaxX()-s.getW(), window.getMaxY()-s.getH());

            if (windowWithShipAdjustment.contains(newPosition.getX(), newPosition.getY()))
            {
                s.setX(newPosition.getX());
                s.setY(newPosition.getY());
            }

            s.tryToShoot();
        }
    }

    public void drawShips(ArrayList<Spaceship> ships)
    {
        for(Spaceship s: ships)
        {
            graphicsContext.drawImage(s.getImageView().getImage(), s.getX(), s.getY(), s.getW(), s.getH());
           // s.drawShip();
        }
    }

    public void updateProjectiles(ArrayList<Projectile> projectiles) {
        for (Projectile p : projectiles) {
            p.tryToMove();
        }
    }

    public void drawProjectiles(ArrayList<Projectile> projectiles) {
        for (Projectile p : projectiles) {
            graphicsContext.drawImage(p.getImageView().getImage(), p.getX(), p.getY(), p.getWidth(), p.getHeight());
        }
    }
}