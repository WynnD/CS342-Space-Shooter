package Game;

/**
 * Created by Wynn on 4/13/2017.
 */
public class Vector2D {
    private double x_component;
    private double y_component;

    public Vector2D() {
        x_component=y_component=0;
    }

    public Vector2D(double x_component, double y_component) {
        this.x_component = x_component;
        this.y_component = y_component;
    }

    public Vector2D(Vector2D vec) {
        this.x_component = vec.getXComponent();
        this.y_component = vec.getYComponent();
    }


    public double getXComponent() {
        return x_component;
    }

    public double getYComponent() {
        return y_component;
    }

    public void addVector(Vector2D vect) {
        this.x_component += vect.getXComponent();
        this.y_component += vect.getYComponent();
    }

    public void addVector(double x_component, double y_component) {
        this.x_component += this.x_component;
        this.y_component += this.y_component;
    }

}
