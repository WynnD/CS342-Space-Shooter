/**
 * Created by Wynn on 3/13/2017.
 */
public class EnemySpaceShip extends SpaceShip {

    public EnemySpaceShip(int x, int y, int h, int w) {
        super(x,y,h,w);
        enemy = true;
        System.out.println("EnemySpaceShip created at (" + x + "," + y + ") with dimensions " + w + "x" + h);
    }

    public void display() {
        System.out.println("EnemySpaceShip is displayed at (" + x + "," + y + ") with dimensions " + w + "x" + h);
    }
}
