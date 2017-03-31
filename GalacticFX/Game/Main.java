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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.BoundingBox;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.event.*;
import java.awt.*;
import java.io.File;
import java.util.*;

//TODO: add musicHandler class, migrate things to collisionHandler (cleanup main)
//TODO: upgrades, levels, scoring, save/pause, multiple enemies, explosion animation

public class Main extends Application {
    private GraphicsContext graphicsContext;
    Button startButton;
    Scene scene;
    private Scene endScene;
    Stage theStage;
    private int seconds;
    private long lastTime;
    private BorderPane gameOver;
    private CollisionHandler collisionHandler;

    @Override
    public void start(Stage primaryStage){

        theStage = primaryStage;
        BorderPane menu = new BorderPane();
        gameOver = new BorderPane();

        MusicPlayer menuSong = new MusicPlayer("Music/spaceIntro.mp3");
        menuSong.playSong();

        //set background image
        Sprite background = new Sprite("Images/stars.jpg", 550, 700);

        double width = background.getWidth();
        double height = background.getHeight();

        //create canvas with size of background image
        Canvas canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();

        //create stackpane for background image and canvas
        StackPane sp = new StackPane();
        sp.getChildren().addAll(background.getImageView(), canvas);

        menu.setStyle("-fx-background-color: black;");
        menu.setPrefSize(width, height);
        startButton = new Button("START");
        startButton.setStyle("-fx-font: 48 impact; -fx-base: #0de818;");
        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                menuSong.pauseSong();
                //menuSong.pauseSong();
                theStage.setScene(scene);
                MusicPlayer gameSong = new MusicPlayer("Music/Orbit.mp3");
                gameSong.playSong();
            }
        });
        menu.setCenter(startButton);

        gameOver.setStyle("-fx-background-color: black;");
        gameOver.setPrefSize(width, height);
        Label endLabel = new Label("VICTORY");
        endLabel.setTextFill(Color.GREENYELLOW);
        Font font = new Font("Arial", 70);
        endLabel.setFont(font);
        gameOver.setCenter(endLabel);

        scene = new Scene(sp);
        Scene menuScene = new Scene(menu);
        endScene = new Scene(gameOver);
        primaryStage.setTitle("Galactic Overdrive 3000");
        primaryStage.setScene(menuScene);

        KeyListen keyListener = new KeyListen(scene);

        ArrayList<Spaceship> ships = new ArrayList<Spaceship>();

        ShipFactory factory = new ShipFactory(graphicsContext, keyListener);
        initializeShips(factory, ships);

        BoundingBox window = new BoundingBox(0, 0, width, height);

        ArrayList<PlayerLife> lives = new ArrayList<PlayerLife>();
        initializeLives(lives);

        //CollisionHandler ch = new CollisionHandler(ships);

        lastTime = 0;
        seconds = 0;

        new AnimationTimer(){
            public void handle(long now){
                if(theStage.getScene() == scene) {
                    //do something every second
                    if(now > lastTime + 1_000_000_000)
                    {
                        seconds++;
                        lastTime = now;
                        //System.out.println(seconds);
                        if(seconds%2 == 0)
                            createProjectiles(ships);
                    }

                        keyListener.listen();
                        updateShips(ships, window);    //Move ships and/or have the ships shoot
                        updateProjectiles(ships, window, lives);
                        deleteFlaggedProjectiles(ships);
                        graphicsContext.clearRect(0, 0, width, height);  //Wipe Screen of all ships
                        drawShips(ships);                   //Draw updated ships
                        drawProjectiles(ships);
                        drawLives(lives);

                        if (ships.size() == 1) {
                            System.out.println("game over");
                            theStage.setScene(endScene);
                        }
                        //drawExplosion(100, 100);
                    }
            }
        }.start();

        primaryStage.show();
    }


    public static void main(String[] args){
        launch(args);
    }

    public void drawLives(ArrayList<PlayerLife> lives)
    {
        for(PlayerLife life : lives)
        {
            life.drawLife(graphicsContext);
        }
    }

    public void initializeLives(ArrayList<PlayerLife> lives)
    {
        double width = graphicsContext.getCanvas().getWidth();
        PlayerLife life1 = new PlayerLife(width-25, 10);
        PlayerLife life2 = new PlayerLife(width-50, 10);
        PlayerLife life3 = new PlayerLife(width-75, 10);
        lives.add(life1);
        lives.add(life2);
        lives.add(life3);
    }

    public void initializeShips(ShipFactory factory, ArrayList<Spaceship> ships)
    {
        double width = graphicsContext.getCanvas().getWidth();
        int midScreen = (int) ((width)/2);
        double height = graphicsContext.getCanvas().getHeight();

        Spaceship userShip = factory.makeShip("User");
        //userShip.setPosition(midScreen, ((int)(height-100)) );
        userShip.setX(midScreen);
        userShip.setY((int) (height-100));

        Spaceship enemyShip = factory.makeShip("Enemy");
        enemyShip.setX(midScreen);
        enemyShip.setY(100);

        Spaceship enemyShip2 = factory.makeShip("Enemy");
        enemyShip2.setX(midScreen - 70);
        enemyShip2.setY(50);

        Spaceship enemyShip3 = factory.makeShip("Enemy");
        enemyShip3.setX(midScreen - 140);
        enemyShip3.setY(100);

        Spaceship enemyShip4 = factory.makeShip("Enemy");
        enemyShip4.setX(midScreen + 70);
        enemyShip4.setY(50);

        Spaceship enemyShip5 = factory.makeShip("Enemy");
        enemyShip5.setX(midScreen + 140);
        enemyShip5.setY(100);

        ships.add(userShip);
        ships.add(enemyShip);
        ships.add(enemyShip2);
        ships.add(enemyShip3);
        ships.add(enemyShip4);
        ships.add(enemyShip5);
    }

    public void createProjectiles(ArrayList<Spaceship> ships)
    {
        for (Spaceship s : ships) {
            if(!s.getShipType().equals("User")) {
                ArrayList<Projectile> projectiles = s.getProjectiles();
                Sprite projectileSprite = new Sprite("Images/laserRed02.png", 8, 15);
                projectiles.add(new Projectile(s, projectileSprite.getImageView(), 5));
            }

            }
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


    public int checkShipCollisions(int newX, int newY, int curShipIndex, ArrayList<Spaceship> ships)
    {
        int shipCollision = -1;
        Spaceship curShip;

        for(int i = 0; i < ships.size(); i++)
        {
            if(i != curShipIndex) //make sure ship isn't being compared with itself
            {
                curShip = ships.get(i);
                Rectangle rect = new Rectangle(); //basically a hitbox for the ship
                rect.setBounds(curShip.getX(), curShip.getY(), (int) curShip.getW(), (int) curShip.getH());
                if (rect.contains(newX, newY)) {
                    shipCollision = i;
                    return shipCollision;
                }
            }
        }

        return shipCollision;
    }

    public void updateShips(ArrayList<Spaceship> ships, BoundingBox window){

        BoundingBox windowWithShipAdjustment;
        int collisionWithShip = -1;

        outerloop:
        for(Spaceship s: ships)
        {

            Coordinate2D newPosition = s.tryToMove();

            if(s.getShipType().equals("User"))
            {
                //returns ship index if collision otherwise -1
                collisionWithShip = checkShipCollisions(newPosition.getX(), newPosition.getY(), ships.indexOf(s), ships);
                if (collisionWithShip != -1)
                {
                    System.out.println("ship collision");
                    explosionSound();

                    //collisionHandler.setGameOverScreen();
                    Label loseLabel = new Label("GAME OVER");
                    loseLabel.setTextFill(Color.RED);
                    Font font = new Font("Arial", 70);
                    loseLabel.setFont(font);
                    gameOver.setCenter(loseLabel);
                    theStage.setScene(endScene);

                    /*System.out.println("ship collision");
                    ships.remove(collisionWithShip);
                    explosionSound();
                    theStage.setScene(endScene);*/
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

    public void updateProjectiles(ArrayList<Spaceship> ships, BoundingBox window, ArrayList<PlayerLife> lives) {

        Coordinate2D newPos;

        int collisionWithShip = -1; //indicates there is no collision if -1

        outerloop: //label used for going back to start of loop
        for (Spaceship s : ships) {
            for (Projectile p : s.getProjectiles()) {
                if (!window.contains(p.getX(), p.getY()))
                {
                    p.destroy();
                } else {
                    newPos = p.tryToMove();
                    collisionWithShip = checkShipCollisions(newPos.getX(), newPos.getY(), ships.indexOf(s), ships);
                    if (collisionWithShip != -1)
                    {
                        s.getProjectiles().remove(p);
                        explosionSound();
                        if(collisionWithShip == 0)
                        {
                            //collisionHandler.handleUserHit(lives);
                            System.out.println("User ship hit");
                            lives.remove(lives.size()-1);
                            System.out.println("num lives: " + lives.size());

                            if(lives.size() == 0) {
                                Label loseLabel = new Label("GAME OVER");
                                loseLabel.setTextFill(Color.RED);
                                Font font = new Font("Arial", 70);
                                loseLabel.setFont(font);
                                gameOver.setCenter(loseLabel);
                                theStage.setScene(endScene);
                            }
                        }
                        else {
                            ships.remove(collisionWithShip);
                        }

                        break outerloop; //go to next ship if this ship had a collision
                    }
                    else
                    {
                        p.setX(newPos.getX());
                        p.setY(newPos.getY());
                    }

                }
            }
        }
    }

    public void explosionSound()
    {
        String musicFile2 = "Music/boom.mp3";
        Media sound2 = new Media(new File(musicFile2).toURI().toString());
        MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
        mediaPlayer2.play();
    }

    public void drawExplosion(double x, double y)
    {
        String imgName = "Images/explosion.png";
        Image explodeImage = new Image(new File(imgName).toURI().toString());
        graphicsContext.drawImage(explodeImage, x, y, 80, 80);
    }

    public void drawProjectiles(ArrayList<Spaceship> ships) {
        for (Spaceship s : ships) {
            for (Projectile p : s.getProjectiles()) {
                graphicsContext.drawImage(p.getImageView().getImage(), p.getX(), p.getY(), p.getWidth(), p.getHeight());
            }
         }
    }
}