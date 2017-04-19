package Game;

import com.sun.deploy.config.VerboseDefaultConfig;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by noemi_000 on 4/6/2017.
 */
public class Spaceship {

    protected MoveBehavior moveBehavior;
    protected ShootBehavior shootBehavior;
    protected String shipType;
    protected Sprite shipSprite;
    protected ArrayList<Projectile> projectiles;
    protected Coordinate2D position;
    protected boolean destroyed;
    protected boolean enemy;

    public Spaceship(String shipType, Sprite shipSprite, Coordinate2D position)
    {
        this.shipType = shipType;
        this.shipSprite = shipSprite;
        this.position = position;
        projectiles = new ArrayList<>();
        destroyed = false;

    }

    public void moveShip(Coordinate2D newPosition)
    {
        this.position = newPosition;
    }

    public Coordinate2D tryToMove(){
        return moveBehavior.getNextPosition();
    }

    public Coordinate2D getPosition() {
        return position;
    }

    public void tryToShoot() {
        shootBehavior.update();
    }

    public void display(GraphicsContext gc) {

        gc.drawImage(shipSprite.getImageView().getImage(), position.getX(), position.getY(), shipSprite.getWidth(), shipSprite.getHeight());
    }

    public String getShipType(){ return shipType; }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public int getX() { return position.getX(); }

    public int getY() { return position.getY(); }

    public double getW() { return shipSprite.getWidth(); }

    public double getH() { return shipSprite.getHeight(); }

    public Sprite getShipSprite()
    {
        return shipSprite;
    }

    public void destroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public boolean isFriendlyWith(Spaceship other_ship) {
        return isEnemy() == other_ship.isEnemy();
    }

}

class UserSpaceship extends Spaceship
{
    private KeyListen keyListener;

    public UserSpaceship(String shipType, Sprite shipSprite, KeyListen keyListener, Coordinate2D position)
    {
        super(shipType, shipSprite, position);

        this.keyListener = keyListener;
        this.moveBehavior = new UserMoveBehavior(this);
        this.shootBehavior = new ShootOneBullet(this, keyListener, projectiles);
        this.enemy = false;
    }


    public KeyListen getKeyListener() {
        return keyListener;
    }

}

abstract class EnemySpaceship extends Spaceship {
    private Vector2D velocity;

    public EnemySpaceship(String shipType, Sprite shipSprite, Coordinate2D position) {
        super(shipType, shipSprite, position);
        this.enemy = true;
    }

    public Vector2D getVelocityVector() {
        return velocity;
    }

    public void setVelocity(Vector2D vect) {
        this.velocity = vect;
    }
}

class EnemySpaceship1 extends EnemySpaceship
{
    private Vector2D velocity;

    public EnemySpaceship1(String shipType, Sprite shipSprite, Coordinate2D position )
    {
        super(shipType, shipSprite, position);
        this.velocity = new Vector2D(-2,0);
        this.moveBehavior = new HorizontalMoveBehavior(this);
    }

    public Vector2D getVelocityVector() {
        return velocity;
    }

    public void setVelocity(Vector2D vect) {
        this.velocity = vect;
    }
}

class EnemySpaceship2 extends EnemySpaceship
{
    private Vector2D velocity;

    public EnemySpaceship2(String shipType, Sprite shipSprite, Coordinate2D position )
    {
        super(shipType, shipSprite, position);
        this.velocity = new Vector2D(-2,0);
        this.moveBehavior = new CircleMoveBehavior(this);
    }

    public Vector2D getVelocityVector() {
        return velocity;
    }

    public void setVelocity(Vector2D vect) {
        this.velocity = vect;
    }
}
