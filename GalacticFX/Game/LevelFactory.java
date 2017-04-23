package Game;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
/**
 * Created by Deon on 4/13/2017.
 */
public class LevelFactory {

    private ArrayList<Spaceship> ships;
    private ShipFactory shipFactory;
    private double width, height;
    private int midScreen;
    private Level level;

    public LevelFactory(ArrayList<Spaceship> ships, GraphicsContext graphicsContext, KeyListen keyListener){

        this.ships = ships;

        width = graphicsContext.getCanvas().getWidth();
        height = graphicsContext.getCanvas().getHeight();
        midScreen = (int)width/2;

        shipFactory = new ShipFactory(keyListener);

    }

    public Level makeLevel(int levelType){

        if(levelType == 1){

            Spaceship userShip = shipFactory.makeShip("User", midScreen, (int)height-100);
            Spaceship enemyShip = shipFactory.makeShip("SaneEnemy", midScreen, 100);
            Spaceship enemyShip2 = shipFactory.makeShip("SaneEnemy", midScreen-70, 50);
            Spaceship enemyShip3 = shipFactory.makeShip("SaneEnemy", midScreen-140, 100);
            Spaceship enemyShip4 = shipFactory.makeShip("SaneEnemy", midScreen+70, 50);
            Spaceship enemyShip5 = shipFactory.makeShip("SaneEnemy", midScreen+140, 100);

            ships.add(userShip);
            ships.add(enemyShip);
            ships.add(enemyShip2);
            ships.add(enemyShip3);
            ships.add(enemyShip4);
            ships.add(enemyShip5);

        } else if(levelType == 2) {
            Spaceship userShip = shipFactory.makeShip("User", midScreen, (int)height-100);
            Spaceship enemyShip = shipFactory.makeShip("DrunkEnemy", midScreen, 100);
            Spaceship enemyShip2 = shipFactory.makeShip("DrunkEnemy", midScreen-70, 50);
            Spaceship enemyShip3 = shipFactory.makeShip("DrunkEnemy", midScreen-140, 100);
            Spaceship enemyShip4 = shipFactory.makeShip("DrunkEnemy", midScreen+70, 50);
            Spaceship enemyShip5 = shipFactory.makeShip("DrunkEnemy", midScreen+140, 100);
            ships.add(userShip);
            ships.add(enemyShip);
            ships.add(enemyShip2);
            ships.add(enemyShip3);
            ships.add(enemyShip4);
            ships.add(enemyShip5);

        } else if(levelType == 3) {
            Spaceship userShip = shipFactory.makeShip("User", midScreen, (int)height-100);
            Spaceship enemyShip = shipFactory.makeShip("DizzyEnemy", midScreen, 100);
            Spaceship enemyShip2 = shipFactory.makeShip("DizzyEnemy", midScreen-70, 50);
            Spaceship enemyShip3 = shipFactory.makeShip("DizzyEnemy", midScreen-140, 100);
            Spaceship enemyShip4 = shipFactory.makeShip("DizzyEnemy", midScreen+70, 50);
            Spaceship enemyShip5 = shipFactory.makeShip("DizzyEnemy", midScreen+140, 100);
            ships.add(userShip);
            ships.add(enemyShip);
            ships.add(enemyShip2);
            ships.add(enemyShip3);
            ships.add(enemyShip4);
            ships.add(enemyShip5);

        }

        level = new Level(ships, levelType);

        return level;
    }
}
