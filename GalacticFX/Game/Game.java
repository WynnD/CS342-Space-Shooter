package Game;

import javafx.geometry.BoundingBox;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import java.awt.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Deon on 4/12/2017.
 */
public class Game {
    private Scene scene;
    private Scene endScene;
    private Stage stage;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Sprite background;
    private StackPane stackPane;
    private long lastTime;
    private int seconds;
    private double width;
    private double height;
    private BorderPane gameOver;
    private KeyListen keyListener;
    private ArrayList<Spaceship> ships;
    private BoundingBox window;
    private ArrayList<PlayerLife> lives;
    private CollisionHandler collisionHandler;

    public Game(Stage stage){

        this.stage = stage;
        setupGameFrame();   //handles all the scene, canvas, bounding box crap :p
        playMusic();

        keyListener = new KeyListen(scene);

        ships = new ArrayList<>();
        lives = new ArrayList<>();
        initializeLives();

        LevelFactory levelFactory = new LevelFactory(ships, graphicsContext, keyListener);
        Level currentLevel = levelFactory.makeLevel("Level1");
        ships = currentLevel.getShips();  //this is how we will refresh our ships array with each new level
                                    //New levels added once ships.size() == 1 ie. only the user ship remains

        collisionHandler = new CollisionHandler(ships, window, lives);

        lastTime = 0;   //variables for timer
        seconds = 0;

        new AnimationTimer(){
            public void handle(long now){
                if(stage.getScene() == scene) {
                    //do something every second
                    if(now > lastTime + 1_000_000_000)  //put this in shoot behavior
                    {
                        seconds++;
                        lastTime = now;
                        System.out.println(seconds);
                        if(seconds%2 == 0)
                            createProjectiles();
                    }

                    update();       //all of the update happens here, could make a class but this is another option, it makes the Game class bigger though
                                    //NOTE: make sure to look in update for some changes, more comments there
                    draw();         //same with draw()
                }
            }
        }.start();


    }

    public void update()
    {
        checkForGameOver();            //This is how we will transfer over to an endgame menu, similar to how we transfered into Game (just send the stage in,
                                        //and change the scene. Currently, the scene change just takes place in this function, but will eventually change.
        keyListener.listen();
        updateShips();
        updateProjectiles();
        deleteFlaggedProjectiles();
        deleteFlaggedShips();
    }

    public void draw()
    {
        graphicsContext.clearRect(0, 0, width, height);  //Wipe Screen of all ships
        drawShips();                   //Draw updated ships
        drawProjectiles();
        drawLives();
    }

    public void setupGameFrame()
    {
        background = new Sprite("Images/stars.jpg", 550, 700);
        width = background.getWidth();
        height = background.getHeight();
        window = new BoundingBox(0, 0, width, height);
        canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();
        stackPane = new StackPane();
        stackPane.getChildren().addAll(background.getImageView(), canvas);
        scene = new Scene(stackPane);
        stage.setScene(scene);
    }

    public void playMusic()
    {
        MusicPlayer gameSong = new MusicPlayer("Music/Orbit.mp3");
        gameSong.playSong();
    }

    public void drawLives()
    {
        for(PlayerLife life : lives)
        {
            life.drawLife(graphicsContext);
        }
    }

    public void initializeLives()
    {
        double width = graphicsContext.getCanvas().getWidth();
        PlayerLife life1 = new PlayerLife(width-25, 10);
        PlayerLife life2 = new PlayerLife(width-50, 10);
        PlayerLife life3 = new PlayerLife(width-75, 10);
        lives.add(life1);
        lives.add(life2);
        lives.add(life3);
    }

    public void createProjectiles()
    {
        for (Spaceship s : ships) {
            if(!s.getShipType().equals("User")) {
                ArrayList<Projectile> projectiles = s.getProjectiles();
                Sprite projectileSprite = new Sprite("Images/laserRed02.png", 8, 15);
                projectiles.add(new Projectile(s, projectileSprite, 5));
            }
        }
    }

    public void deleteFlaggedProjectiles()
    {
        for (Spaceship s : ships) {
            ArrayList<Projectile> projectiles = s.getProjectiles();
            for (int i = 0; i < projectiles.size(); ++i) {
                if (projectiles.get(i).destroyed()) {
                    projectiles.remove(i);
                }
            }
        }
    }

    public void deleteFlaggedShips()
    {
        for (int i = ships.size()-1; i>=0; i--) {
            if(ships.get(i).destroyed())
                ships.remove(i);
        }
    }

    public void checkForGameOver(){
        if (ships.size() == 1) {                  //Winner! NOTE: once more levels are added, also check
                                                                //to make sure currentlevel == finallevel
            gameOver = new BorderPane();
            gameOver.setStyle("-fx-background-color: black;");
            gameOver.setPrefSize(width, height);
            Label endLabel = new Label("VICTORY");
            endLabel.setTextFill(Color.GREENYELLOW);
            Font font = new Font("Arial", 70);
            endLabel.setFont(font);
            gameOver.setCenter(endLabel);
            System.out.println("game over");
            endScene = new Scene(gameOver);
            stage.setScene(endScene);
        }

        else if (lives.size() == 0){
            gameOver = new BorderPane();                       //Loser
            gameOver.setStyle("-fx-background-color: black;");
            gameOver.setPrefSize(width, height);
            Label endLabel = new Label("DEFEAT");
            endLabel.setTextFill(Color.RED);
            Font font = new Font("Arial", 70);
            endLabel.setFont(font);
            gameOver.setCenter(endLabel);
            System.out.println("game over");
            endScene = new Scene(gameOver);
            stage.setScene(endScene);
        }

    }

    public void updateShips()
    {
        int collisionWithShip = -1;

        for(Spaceship s: ships)
        {
            Coordinate2D newPosition = s.tryToMove();

            collisionWithShip = collisionHandler.checkShipCollisions(newPosition, s);
            if(collisionWithShip != -1) {
                collisionHandler.handleShipCollision(ships.indexOf(s), collisionWithShip);
                return;
            }

            if(collisionHandler.shipInBounds(newPosition, s))
                s.moveShip(newPosition);


            if(s.getShipType().equals("User"))
                s.tryToShoot();
        }
    }

    public void drawShips()
    {
        for(Spaceship s: ships)
        {
            s.display(graphicsContext);
        }
    }

    public void updateProjectiles()
    {
        Coordinate2D newPos;

        int collisionWithShip = -1;

        outerloop: //label used for going back to start of loop
        for (Spaceship s : ships) {
            for (Projectile p : s.getProjectiles()) {
                if(!collisionHandler.projectileInBounds(p))
                {
                    p.destroy();
                }
                else {
                    newPos = p.tryToMove();
                    collisionWithShip = collisionHandler.checkProjectileCollisions(newPos, s);
                    if (collisionWithShip != -1)
                    {
                        collisionHandler.handleProjectileCollision(s, p, collisionWithShip);
                        break outerloop; //go to next ship if this ship had a collision
                    }
                    else
                    {
                        p.setPosition(newPos);
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
        javafx.scene.image.Image explodeImage = new javafx.scene.image.Image(new File(imgName).toURI().toString());
        graphicsContext.drawImage(explodeImage, x, y, 80, 80);
    }

    public void drawProjectiles()
    {
        for (Spaceship s : ships) {
            for (Projectile p : s.getProjectiles()) {
                p.display(graphicsContext);
            }
        }
    }

}
