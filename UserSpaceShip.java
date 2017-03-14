/**
 * Created by Wynn on 3/13/2017.
 */
public class UserSpaceShip extends SpaceShip {
    private boolean enemy = false;

    public UserSpaceShip(int x, int y, int h, int w) {
        super(x,y,h,w);
        enemy = false;
        System.out.println("UserSpaceShip created at (" + x + "," + y + ") with dimensions " + w + "x" + h);
    }

    public void display() {
        System.out.println("UserSpaceShip is displayed at (" + x + "," + y + ") with dimensions " + w + "x" + h);
    }
}
