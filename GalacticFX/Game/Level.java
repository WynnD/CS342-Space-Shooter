package Game;

import java.util.ArrayList;

/**
 * Created by Deon on 4/13/2017.
 */
public class Level {
    private ArrayList<Spaceship> ships;
    private String levelType;

    public Level(ArrayList<Spaceship> ships, String levelType){
        this.ships = ships;
        this.levelType = levelType;
    }

    public ArrayList<Spaceship> getShips(){
        return ships;
    }

    public String getLevelType(){   //will be used to check current level in Game
        return levelType;
    }
}
