package Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.geometry.BoundingBox;
import javafx.scene.layout.*;
import javafx.event.*;
import java.awt.*;
import java.io.File;
import java.util.*;

//TODO: weapons/shooting, main menu, moveBehavior, collision checking,
//TODO: upgrades, levels, scoring, save/pause, sound effects

public class Main extends Application {
    private GraphicsContext graphicsContext;
    Button startButton;
    Scene scene;
    Stage theStage;

    @Override
    public void start(Stage primaryStage){
        theStage = primaryStage;
        BorderPane menu = new BorderPane();

        //music to be played during game
        String musicFile1 = "spaceIntro.mp3";
        Media menuSound = new Media(new File(musicFile1).toURI().toString());
        MediaPlayer mediaPlayerMenu = new MediaPlayer(menuSound);
        mediaPlayerMenu.setCycleCount(MediaPlayer.INDEFINITE); //loops music
        mediaPlayerMenu.play();

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

        menu.setStyle("-fx-background-color: black;");
        menu.setPrefSize(width, height);
        startButton = new Button("START");
        startButton.setStyle("-fx-font: 36 impact; -fx-base: #4286f4;");
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                mediaPlayerMenu.pause();
                theStage.setScene(scene);
                String musicFile = "Orbit.mp3";
                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //loops music
                mediaPlayer.play();
            }
        });

        menu.setCenter(startButton);
        scene = new Scene(sp);
        Scene menuScene = new Scene(menu);
        primaryStage.setTitle("Galactic Overdrive 3000");
        primaryStage.setScene(menuScene);


        KeyListen keyListener = new KeyListen(scene);

        ArrayList<Spaceship> ships = new ArrayList<Spaceship>();

        int midScreen = (int) ((width)/2);

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
        //CollisionHandler ch = new CollisionHandler(ships);

        new AnimationTimer(){
            public void handle(long currentNanoTime){
                    keyListener.listen();
                    updateShips(ships, window);    //Move ships and/or have the ships shoot
                    updateProjectiles(ships, window);
                    deleteFlaggedProjectiles(ships);
                    graphicsContext.clearRect(0, 0, width, height);  //Wipe Screen of all ships
                    drawShips(ships);                   //Draw updated ships
                    drawProjectiles(ships);
            }
        }.start();

        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

    public void deleteFlaggedProjectiles(ArrayList<Spaceship> ships) {
        for (Spaceship s : ships) {
            ArrayList<Projectile> projectiles = s.getProjectiles();
            for (int i = 0; i < projectiles.size(); ++i) {
                if (projectiles.get(i).destroyed()) {
                    projectiles.remove(i);
                }
            }
        }
    }

    public void updateProjectiles(ArrayList<Spaceship> ships, BoundingBox window) {
        for (Spaceship s : ships) {
            for (Projectile p : s.getProjectiles()) {
                if (!window.contains(p.getX(), p.getY()))
                {
                    p.destroy();
                } else {
                    p.tryToMove();
                }
            }
        }
    }

    public void drawProjectiles(ArrayList<Spaceship> ships) {
        for (Spaceship s : ships) {
            for (Projectile p : s.getProjectiles()) {
                graphicsContext.drawImage(p.getImageView().getImage(), p.getX(), p.getY(), p.getWidth(), p.getHeight());
            }
        }
    }

    public int checkShipCollisions(int newX, int newY, ArrayList<Spaceship> ships)
    {
        int shipCollision = -1;
        Spaceship curShip;

        for(int i = 1; i < ships.size(); i++)
        {
            curShip = ships.get(i);
            Rectangle rect = new Rectangle(); //basically a hitbox for the ship
            rect.setBounds(curShip.getX(), curShip.getY(), (int)curShip.getW(), (int)curShip.getH());
            if(rect.contains(newX, newY))
            {
                shipCollision = i;
                return shipCollision;
            }
        }

        return shipCollision;
    }

    public void updateShips(ArrayList<Spaceship> ships, BoundingBox window){

        BoundingBox windowWithShipAdjustment;
        int collisionWithShip = -1;

        for(Spaceship s: ships)
        {

            Coordinate2D newPosition = s.tryToMove();

            if(s.getShipType().equals("User"))
            {
                //returns ship index if collision otherwise -1
                collisionWithShip = checkShipCollisions(newPosition.getX(), newPosition.getY(), ships);
                if (collisionWithShip != -1)
                {
                    System.out.println("ship collision");
                    //ships.remove(collisionWithShip);
                }
            }

            // get bounding box with adjustment for ship size
            windowWithShipAdjustment = new BoundingBox(0,0,window.getMaxX()-s.getW(), window.getMaxY()-s.getH());

            if (windowWithShipAdjustment.contains(newPosition.getX(), newPosition.getY()) && collisionWithShip == -1)
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
           s.drawShip();
        }
    }
}
