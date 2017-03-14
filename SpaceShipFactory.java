/**
 * Created by Wynn on 3/13/2017.
 */
public class SpaceShipFactory {

    public SpaceShip makeShip(String type, int x, int y, int w, int h) {
        if (type.equals("user")) {
            return new UserSpaceShip(x,y,w,h);
        } else if (type.equals("enemy")) {
            return new EnemySpaceShip(x,y,w,h);
        } else {
            return null;
        }
    }
}
