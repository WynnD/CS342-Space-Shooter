package Game;

import java.util.ArrayList;

/**
 * Created by Deon on 4/13/2017.
 */
public class Level {
    private ArrayList<Spaceship> ships;
    //private String levelType;
    private int levelType;
    //private MusicPlayer musicPlayer;

    public Level(ArrayList<Spaceship> ships, int levelType){
        this.ships = ships;
        this.levelType = levelType;

    }

    public ArrayList<Spaceship> getShips(){
        return ships;
    }

    public int getLevelType(){   //will be used to check current level in Game
        return levelType;
    }
}
