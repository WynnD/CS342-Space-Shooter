package Game;

//import com.sun.prism.paint.Color;
import javafx.geometry.BoundingBox;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Deon on 4/12/2017.
 */
public class Game {
    private Scene scene;
    private Stage stage;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Sprite background;
    private StackPane stackPane;
    private long lastTime;
    private int curSec = 0;
    private int seconds;
    private double width;
    private double height;
    private KeyListen keyListener;
    private ArrayList<Spaceship> ships;
    private BoundingBox window;
    private ArrayList<PlayerLife> lives;
    private CollisionHandler collisionHandler;
    private final AnimationTimer timer;
    private MusicPlayer gameSong;
    private Level currentLevel;
    private LevelFactory levelFactory;
    private Scoreboard scoreboard;

    public Game(Stage stage){

        this.stage = stage;
        initialize();
        Image startImage = new Image(new File("Images/start.jpg").toURI().toString());
        timer = new AnimationTimer(){
            public void handle(long now){
                if(stage.getScene() == scene) {
                    //do something every second
                    if(now > lastTime + 1_000_000_000)  //put this in shoot behavior
                    {
                        if (seconds > 2) {
                            scoreboard.addScore(5);
                        }
                        seconds++;
                        lastTime = now;
                        System.out.println(seconds);
                        if(seconds%2 == 0)
                            createProjectiles();
                    }

                    if(seconds > 2) {
                        update();       //all of the update happens here, could make a class but this is another option, it makes the Game class bigger though
                        //NOTE: make sure to look in update for some changes, more comments there
                        draw();         //same with draw()
                    }
                    else
                    {
                        //display start! message, gives user time before enemies attack
                        graphicsContext.drawImage(startImage, 100, 300, 350, 100);
                    }
                }
            }
        };

        timer.start();
    }

    public void initialize()
    {
        setupGameFrame();   //handles all the scene, canvas, bounding box crap :p
        playMusic();

        keyListener = new KeyListen(scene);

        ships = new ArrayList<>();
        lives = new ArrayList<>();
        initializeLives();

        levelFactory = new LevelFactory(ships, graphicsContext, keyListener);
        currentLevel = levelFactory.makeLevel(1);
        ships = currentLevel.getShips();
        collisionHandler = new CollisionHandler(ships, window, lives, scoreboard);

        lastTime = 0;   //variables for timer
        seconds = 0;
    }

    public void startTimer()
    {
        timer.start();
    }

    public void update()
    {
        checkForGameOver();            //This is how we will transfer over to an endgame menu, similar to how we transfered into Game (just send the stage in,
                                        //and change the scene. Currently, the scene change just takes place in this function, but will eventually change.
        keyListener.listen();
        updateShips();
        updateProjectiles();
        deleteFlaggedProjectiles();
        //deleteFlaggedShips();
    }

    public void draw()
    {
        graphicsContext.clearRect(0, 0, width, height);  //Wipe Screen of all ships
        drawScoreboard();
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
        initScoreboard();
        stackPane = new StackPane();
        stackPane.getChildren().addAll(background.getImageView(), canvas);
        scene = new Scene(stackPane);
        stage.setScene(scene);
    }

    public void initScoreboard() {
        scoreboard = new Scoreboard();
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font("Verdana",20));
    }

    public void playMusic()
    {
        gameSong = new MusicPlayer("Music/Orbit.mp3");
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
                if (projectiles.get(i).isDestroyed()) {
                    projectiles.remove(i);
                }
            }
        }
    }

    /*
    public void deleteFlaggedShips()
    {
        for (int i = ships.size()-1; i>=0; i--) {
            if(ships.get(i).isDestroyed()) {
                if (ships.get(i).isEnemy()) {
                    System.out.println("Added 100 to score for total of " + scoreboard.getScore() + " points");
                    scoreboard.addScore(100);
                }
                ships.remove(i);
            }
        }
    }
    */
    public void checkForGameOver(){
        if (ships.size() == 1) {
            if(currentLevel.getLevelType() == 3){
                gameSong.pauseSong();
                timer.stop();
                new EndGameMenu(this, stage, "victory");
                stage.show();
              }
             else
              {
                int curLevelType = currentLevel.getLevelType();
                int newLevel = curLevelType + 1;
                ships.remove(0);
                currentLevel = levelFactory.makeLevel(newLevel);
                ships = currentLevel.getShips();
                collisionHandler = new CollisionHandler(ships, window, lives, scoreboard);
             }
        }
        else if (lives.size() == 0){
            gameSong.pauseSong();
            timer.stop();
            new EndGameMenu(this, stage, "game over");
            stage.show();
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

        int j = 0;
        int collisionWithShip = -1;

        // foreach loop causes checkforcomodificationerror... hmm
        for (int i = 0; i < ships.size(); i++) {
            Spaceship s = ships.get(i);
            j++;
            for (Projectile p : s.getProjectiles()) {
                if (!collisionHandler.projectileInBounds(p)) {
                    p.destroy();
                } else {
                    newPos = p.getNextPosition(j);
                    collisionWithShip = collisionHandler.checkProjectileCollisions(newPos, s);
                    if (collisionWithShip != -1) {
                        curSec = seconds;
                        //if(collisionWithShip != 0)
                        collisionHandler.handleProjectileCollision(s, p, collisionWithShip);
                        break; //go to next ship if this ship had a collision
                    } else {
                        p.setPosition(newPos);
                    }
                }
            }
        }
    }

    public void drawProjectiles()
    {
        for (Spaceship s : ships) {
            for (Projectile p : s.getProjectiles()) {
                p.display(graphicsContext);
            }
        }
    }

    public void drawScoreboard() {
        scoreboard.display(graphicsContext);
    }

}
