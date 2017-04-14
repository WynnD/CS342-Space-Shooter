package Game;

import java.awt.*;
import java.io.File;
import java.util.*;
import javafx.geometry.BoundingBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class CollisionHandler {

    private ArrayList<Spaceship> ships;
    private ArrayList<PlayerLife> lives;
    private BoundingBox window;

    public CollisionHandler(ArrayList<Spaceship> ships, BoundingBox window, ArrayList<PlayerLife> lives) {
        this.ships = ships;
        this.window = window;
        this.lives = lives;
    }

    //returns index of ship collided with
    public int checkShipCollisions(Coordinate2D newPosition, Spaceship ship)
    {
        int shipCollision = -1;
        int curShipIndex = ships.indexOf(ship);
        Spaceship curShip;

        for(int i = 0; i < ships.size(); i++)
        {
            if(i != curShipIndex) {
                curShip = ships.get(i);
                //possibly make each ship have a boundingBox?
                BoundingBox shipHitBox = new BoundingBox(curShip.getX(), curShip.getY(), curShip.getW(), curShip.getH());
                if (shipHitBox.intersects(newPosition.getX(), newPosition.getY(), ship.getW(), ship.getH())) {
                    shipCollision = i;
                    return shipCollision;
                }
            }
        }
        return shipCollision;
    }

    public int checkProjectileCollisions(Coordinate2D newPosition, Spaceship ship)
    {
        int shipCollision = -1;
        int curShipIndex = ships.indexOf(ship);
        Spaceship curShip;

        for(int i = 0; i < ships.size(); i++)
        {
            if(i != curShipIndex) {
                curShip = ships.get(i);
                //possibly make each ship have a boundingBox?
                BoundingBox shipHitBox = new BoundingBox(curShip.getX(), curShip.getY(), curShip.getW(), curShip.getH());
                if (shipHitBox.contains(newPosition.getX(), newPosition.getY())){
                    shipCollision = i;
                    return shipCollision;
                }
            }
        }
        return shipCollision;
    }

    public boolean shipInBounds(Coordinate2D newPosition, Spaceship ship)
    {
        if(window.contains(newPosition.getX(), newPosition.getY(), ship.getW(), ship.getH()))
        {
            return true;
        }

        return false;
    }

    public boolean projectileInBounds(Projectile projectile)
    {
        if(window.contains(projectile.getX(), projectile.getY()))
        {
            return true;
        }

        return false;
    }

    public void handleShipCollision(int currentShip, int shipCollidedWith)
    {
        System.out.println("ship collision: " + currentShip + " collided with " + shipCollidedWith);
        explosionSound();
        if(currentShip == 0) {
            handleUserHit();
            ships.get(shipCollidedWith).destroy();
        }
        else if(shipCollidedWith == 0){
            handleUserHit();
            ships.get(currentShip).destroy();
        }

        //OPTIONS: either game over OR remove ship and take life away
    }


    public void handleProjectileCollision(Spaceship s, Projectile p, int shipIndex)
    {
        s.getProjectiles().remove(p);
        explosionSound();
        if(shipIndex == 0)
        {
            handleUserHit();
        }
        else {
            ships.remove(shipIndex);
        }
    }

    public void handleUserHit()
    {
        System.out.println("User ship hit");
        lives.remove(lives.size() - 1);
        System.out.println("num lives: " + lives.size());
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
        //graphicsContext.drawImage(explodeImage, x, y, 80, 80);
    }

}

