package Game;

/**
 * Created by Wynn on 3/26/2017.
 */
public class Coordinate2D {
    private int x, y;

    public Coordinate2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void translateX(int offset) {
        setX(getX()+offset);
    }

    public void translateY(int offset) {
        setY(getY()+offset);
    }
}