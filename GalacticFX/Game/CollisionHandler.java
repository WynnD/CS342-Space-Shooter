package Game;

import java.awt.*;
import java.util.*;
import javafx.scene.image.ImageView;
import javafx.geometry.BoundingBox;

/*
public class CollisionHandler {

    ArrayList<Spaceship> ships;
    //thestage
    //all panes
    //window (for border collisions)


    public CollisionHandler(ArrayList<Spaceship> ships) {
        this.ships = ships;
    }

    public boolean detectBorderCollision(int x, int y) {
        if ((x >= w || x <= 0) || (y <= 0 || y >= h))
            return true;
        else
            return false;
    }

    public boolean detectShipCollision(ImageView shipImage) {
        for (Spaceship s : ships) {
            if(s.getImageView())
        }

    }

    public void handleUserHit(ArrayList<PlayerLife> lives)
    {
        System.out.println("User ship hit");
        lives.remove(lives.size()-1);
        System.out.println("num lives: " + lives.size());

        if(lives.size() == 0) {
           setGameOverScreen();
        }
    }

    public void setGameOverScreen()
    {
        Label loseLabel = new Label("GAME OVER");
        loseLabel.setTextFill(Color.RED);
        Font font = new Font("Arial", 70);
        loseLabel.setFont(font);
        gameOver.setCenter(loseLabel);
        theStage.setScene(endScene);
    }

    public void handleShipCollision()
    {

    }



}

*/