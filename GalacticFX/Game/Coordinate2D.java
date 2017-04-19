package Game;

/**
 * Created by Wynn on 3/26/2017.
 */
public class Coordinate2D {
    private double x, y;

    public Coordinate2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate2D(Coordinate2D c) {
        this.x = c.getX();
        this.y = c.getY();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void translateX(double offset) {
        setX(getX()+offset);
    }

    public void translateY(double offset) {
        setY(getY()+offset);
    }

    public void applyVelocity(Vector2D vector) {
        this.x += vector.getXComponent();
        this.y += vector.getYComponent();
    }
}