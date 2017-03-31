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

    @Override
    public void start(Stage primaryStage){

        theStage = primaryStage;
        BorderPane menu = new BorderPane();
        gameOver = new BorderPane();

        //music to be played during game
        String musicFile1 = "spaceIntro.mp3";
        Media menuSound = new Media(new File(musicFile1).toURI().toString());
        MediaPlayer mediaPlayerMenu = new MediaPlayer(menuSound);
        mediaPlayerMenu.setCycleCount(MediaPlayer.INDEFINITE); //loops music
        mediaPlayerMenu.play();

        //set background image
        String backgroundImageName = "stars.jpg";
        Image background = new Image(new File(backgroundImageName).toURI().toString());
        //Image background = new Image("http://orig10.deviantart.net/dda0/f/2014/285/2/f/free_for_use_galaxy_background_by_duskydeer-d82jaky.png");
        ImageView imgView = new ImageView(background);

        imgView.setFitHeight(700);
        imgView.setFitWidth(550);
        double width = imgView.getFitWidth();
        double height = imgView.getFitHeight();
        //double width = background.getWidth();
        //double height = background.getHeight();

        //create canvas with size of background image
        Canvas canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();

        //create stackpane for background image and canvas
        StackPane sp = new StackPane();
        sp.getChildren().addAll(imgView, canvas);

        menu.setStyle("-fx-background-color: black;");
        menu.setPrefSize(width, height);
        startButton = new Button("START");
        startButton.setStyle("-fx-font: 36 impact; -fx-base: #0de818;");
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

        gameOver.setStyle("-fx-background-color: black;");
        gameOver.setPrefSize(width, height);
        Label endLabel = new Label("VICTORY");
        endLabel.setTextFill(Color.GREENYELLOW);
        Font font = new Font("Arial", 60);
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

        ArrayList<playerLife> lives = new ArrayList<playerLife>();
        playerLife life1 = new playerLife(width-25, 10);
        playerLife life2 = new playerLife(width-50, 10);
        playerLife life3 = new playerLife(width-75, 10);
        lives.add(life1);
        lives.add(life2);
        lives.add(life3);

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

    public void drawLives(ArrayList<playerLife> lives)
    {
        for(playerLife life : lives)
        {
            life.drawLife(graphicsContext);
        }
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
                String projectileImageName = "laserRed02.png";
                Image projectileImage = new Image(new File(projectileImageName).toURI().toString());
                ImageView projectileImageView = new ImageView(projectileImage);
                projectileImageView.setFitHeight(15);
                projectileImageView.setFitWidth(8);
                projectiles.add(new Projectile(s, projectileImageView, 5));
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
                    ships.remove(collisionWithShip);
                    explosionSound();
                    theStage.setScene(endScene);
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

    public void updateProjectiles(ArrayList<Spaceship> ships, BoundingBox window, ArrayList<playerLife> lives) {

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
                            System.out.println("User ship hit");
                            lives.remove(lives.size()-1);
                            System.out.println("num lives: " + lives.size());

                            if(lives.size() == 0) {
                                Label loseLabel = new Label("GAME OVER");
                                loseLabel.setTextFill(Color.RED);
                                Font font = new Font("Arial", 60);
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
        String musicFile2 = "boom.mp3";
        Media sound2 = new Media(new File(musicFile2).toURI().toString());
        MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
        mediaPlayer2.play();
    }

    public void drawExplosion(double x, double y)
    {
        String imgName = "explosion.png";
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